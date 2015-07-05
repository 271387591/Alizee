package com.ozstrategy.webapp.controller.games;

import com.ozstrategy.Constants;
import com.ozstrategy.model.commend.CommendType;
import com.ozstrategy.model.games.Game;
import com.ozstrategy.model.games.GamePlatform;
import com.ozstrategy.model.system.Activity;
import com.ozstrategy.service.commend.CommendManager;
import com.ozstrategy.service.commend.CommentManager;
import com.ozstrategy.service.games.GameManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.games.GameCommand;
import com.ozstrategy.webapp.command.system.ActivityCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
* Created by lihao1 on 2015-06-28.
*/
@RestController
@RequestMapping("game")
public class GameController extends BaseController {
    @Autowired
    private GameManager gameManager;
    @Autowired
    private CommentManager commentManager;
    @Autowired
    private CommendManager commendManager;
    @RequestMapping("list")
    public JsonReaderResponse<GameCommand> list(HttpServletRequest request){
        List<GameCommand> commands=new ArrayList<GameCommand>();
        Map<String,Object> map=requestMap(request);
        try{
        List<Game> models= gameManager.list(map,obtainStart(request),obtainLimit(request));
            if(models!=null && models.size()>0){
                for(Game model:models){
                    GameCommand command=new GameCommand(model);Map<String,Object> objectMap=new HashMap<String, Object>();
                    objectMap.put("Q_itemId_EQ",model.getId());
                    objectMap.put("Q_typeId_EQ", CommendType.Activity.ordinal());
                    Integer comment=commentManager.count(objectMap);
                    Integer commend=commendManager.count(objectMap);
                    command.setComment(comment);
                    command.setCommend(commend);

                    commands.add(command);
                }
            }
            Integer count=gameManager.count(map);
            return new JsonReaderResponse(commands,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("security/gameList")
    public ModelAndView activityList(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("admin/games/gameList");
    }
    @RequestMapping("security/addGame")
    public ModelAndView addActivity(HttpServletRequest request){
        GameCommand command=null;
        try{

            String id=request.getParameter("id");
            if(id==null){
                command=new GameCommand();
            }else{
                command=new GameCommand(gameManager.get(parseLong(id)));
            }
        }catch (Exception e){
            logger.error("addActivity fail",e);
        }
        return new ModelAndView("admin/games/addGame","command",command);
    }
    @RequestMapping(value = "security/upload")
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {
        String username    = request.getRemoteUser();
        String title        = request.getParameter("name");
        String description = request.getParameter("description");
        String id = request.getParameter("id");
        String popularity = request.getParameter("popularity");
        String version = request.getParameter("version");
        String platform = request.getParameter("platform");
        String type = request.getParameter("type");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer = null;

        try {
            writer = response.getWriter();
        } catch (IOException e) { }

        if (writer == null) {
            return null;
        }

        String attachFilesDirStr = request.getSession().getServletContext().getRealPath("/") + "/" + Constants.updloadGame + "/";
        attachFilesDirStr = FilenameUtils.normalize(attachFilesDirStr);


        File fileDir = new File(attachFilesDirStr);

        if (fileDir.exists() == false) {
            fileDir.mkdirs();
        }

        File fileOnServer = null;

        try {
            Game advert=null;
            if(StringUtils.isEmpty(id)){
                advert=new Game();
                advert.setCreateDate(new Date());
            }else{
                advert=gameManager.get(parseLong(id));

            }
            advert.setName(title);
            advert.setDescription(description);
            advert.setPopularity(parseInteger(popularity));
            advert.setVersion(version);
            advert.setType(type);
            advert.setPlatform(GamePlatform.valueOf(platform).ordinal());
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator list             = multipartRequest.getFileNames();
            while (list.hasNext()) {
                String fileName=null,str=null,ext=null;
                String               controlName = list.next().toString();
                MultipartFile file        = multipartRequest.getFile(controlName);
                CommonsMultipartFile cmf         = (CommonsMultipartFile) file;
                DiskFileItem fileItem    = (DiskFileItem) cmf.getFileItem();
                str         = UUID.randomUUID().toString();
                fileName    = fileItem.getName();
                ext         = FilenameUtils.getExtension(fileName);
                String attachFilesDir = attachFilesDirStr + "/" + str + "." + ext;
                attachFilesDir = FilenameUtils.normalize(attachFilesDir);
                fileOnServer      = new File(attachFilesDir);

                if (fileOnServer.exists()) {
                    str               = UUID.randomUUID().toString();
                    attachFilesDir = attachFilesDirStr + "/" + str + "." + ext;
                    attachFilesDir = FilenameUtils.normalize(attachFilesDir);
                    fileOnServer      = new File(attachFilesDir);
                }
                fileItem.write(fileOnServer);
                if(StringUtils.equals(controlName,"picName")){
                    if(StringUtils.isNotEmpty(fileName)){
                        try{
                            FileUtils.forceDelete(new File(advert.getPicPath()));
                        }catch (Exception e){
                        }
                        advert.setPicName(fileName);
                        advert.setPicPath(fileOnServer.getAbsolutePath());
                        String httpPath=toHttpUrl(request,true)+Constants.updloadGame+"/"+str+"."+ext;
                        advert.setPicUrl(httpPath);
                    }
                }else if(StringUtils.equals(controlName,"gameName")){
                    if(StringUtils.isNotEmpty(fileName)){
                        try{
                            FileUtils.forceDelete(new File(advert.getGamePath()));
                        }catch (Exception e){
                        }
                        advert.setGameName(fileName);
                        advert.setGamePath(fileOnServer.getAbsolutePath());
                        String httpPath=toHttpUrl(request,true)+Constants.updloadGame+"/"+str+"."+ext;
                        advert.setGameUrl(httpPath);
                    }
                }

            }
            gameManager.saveOrUpdate(advert);
            writer.print("{\"success\":true,\"msg\":\"\"}");

        } catch (Exception e) {
            logger.error("upload activity fail", e);

            String msg = "保存失败";
            writer.print("{\"success\":false,\"msg\":\"" + msg + "!\"}");
            e.printStackTrace();

            if (fileOnServer != null) {
                try {
                    FileUtils.forceDelete(fileOnServer);
                } catch (IOException e1) { }
            }

        } // end try-catch

        writer.close();

        return null;
    }
    @RequestMapping("security/delete")
    public JsonReaderSingleResponse<ActivityCommand> delete(HttpServletRequest request){
        try{
            String ids=request.getParameter("ids");
            List<Game> commentses=new ArrayList<Game>();
            if(StringUtils.isNotEmpty(ids)){
                String[] ida=ids.split(",");
                for(String id:ida){
                    Game comments=gameManager.get(parseLong(id));
                    commentses.add(comments);
                }
            }
            gameManager.batchDelete(commentses);
            for(Game advert:commentses){
                try{
                    FileUtils.forceDelete(new File(advert.getPicPath()));
                    FileUtils.forceDelete(new File(advert.getGamePath()));
                }catch (Exception e){
                }
            }
            return new JsonReaderSingleResponse("",Boolean.TRUE);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
