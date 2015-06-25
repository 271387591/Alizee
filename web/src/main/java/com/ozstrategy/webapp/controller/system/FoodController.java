package com.ozstrategy.webapp.controller.system;

import com.ozstrategy.Constants;
import com.ozstrategy.model.system.Advert;
import com.ozstrategy.model.system.Food;
import com.ozstrategy.service.system.FoodManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.system.AdvertCommand;
import com.ozstrategy.webapp.command.system.FoodCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
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
* Created by lihao1 on 2015-06-21.
*/
@RestController
@RequestMapping("food")
public class FoodController extends BaseController {
    @Autowired
    private FoodManager foodManager;
    @RequestMapping("list")
    public JsonReaderResponse<FoodCommand> list(HttpServletRequest request){
        List<FoodCommand> commands=new ArrayList<FoodCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<Food> models= foodManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(Food model:models){
                        FoodCommand command=new FoodCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=foodManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("security/addFood")
    public ModelAndView addAdvert(HttpServletRequest request){
        FoodCommand command=null;
        try{

            String id=request.getParameter("id");
            if(id==null){
                command=new FoodCommand();
            }else{
                command=new FoodCommand(foodManager.get(parseLong(id)));
            }
        }catch (Exception e){
            logger.error("addAdvert fail",e);
        }
        return new ModelAndView("admin/system/addFood","command",command);
    }
    @RequestMapping(value = "security/upload")
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {
        String username    = request.getRemoteUser();
        String title        = request.getParameter("title");
        String description = request.getParameter("description");
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer = null;

        try {
            writer = response.getWriter();
        } catch (IOException e) { }

        if (writer == null) {
            return null;
        }



        String attachFilesDirStr = request.getSession().getServletContext().getRealPath("/") + "/" + Constants.updloadAdvert + "/";
        attachFilesDirStr = FilenameUtils.normalize(attachFilesDirStr);



        File fileDir = new File(attachFilesDirStr);

        if (fileDir.exists() == false) {
            fileDir.mkdirs();
        }

        File fileOnServer = null;
        String fileName=null,str=null,ext=null;
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator list             = multipartRequest.getFileNames();
            while (list.hasNext()) {
                String               controlName = list.next().toString();
                MultipartFile file        = multipartRequest.getFile(controlName);
                CommonsMultipartFile cmf         = (CommonsMultipartFile) file;
                DiskFileItem fileItem    = (DiskFileItem) cmf.getFileItem();
                str         = UUID.randomUUID().toString();
                fileName    = fileItem.getName();
                ext         = FilenameUtils.getExtension(fileName);
                attachFilesDirStr = attachFilesDirStr + "/" + str + "." + ext;
                attachFilesDirStr = FilenameUtils.normalize(attachFilesDirStr);
                fileOnServer      = new File(attachFilesDirStr);

                if (fileOnServer.exists()) {
                    str               = UUID.randomUUID().toString();
                    attachFilesDirStr = attachFilesDirStr + "/" + str + "." + ext;
                    attachFilesDirStr = FilenameUtils.normalize(attachFilesDirStr);
                    fileOnServer      = new File(attachFilesDirStr);
                }
                fileItem.write(fileOnServer);
                Food advert=null;
                if(StringUtils.isEmpty(id)){
                    advert=new Food();
                    advert.setCreateDate(new Date());
                }else{
                    advert=foodManager.get(parseLong(id));

                }
                advert.setTitle(title);
                advert.setDescription(description);
                if(StringUtils.isNotEmpty(fileName)){
                    try{
                        FileUtils.forceDelete(new File(advert.getPicPath()));
                    }catch (Exception e){
                    }
                    advert.setPicName(fileName);
                    advert.setPicPath(fileOnServer.getAbsolutePath());
                    String httpPath=toHttpUrl(request,true)+Constants.updloadAdvert+"/"+str+"."+ext;
                    advert.setUrl(httpPath);
                }
                foodManager.saveOrUpdate(advert);
                writer.print("{\"success\":true,\"msg\":\"" + "" + "!\"}");
            }

        } catch (Exception e) {
            logger.error("upload sensor fail", e);

            String msg = "上传失败";
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
    public JsonReaderSingleResponse<FoodCommand> delete(HttpServletRequest request){
        try{
            String ids=request.getParameter("ids");
            List<Food> commentses=new ArrayList<Food>();
            if(StringUtils.isNotEmpty(ids)){
                String[] ida=ids.split(",");
                for(String id:ida){
                    Food comments=foodManager.get(parseLong(id));
                    commentses.add(comments);
                }
            }
            foodManager.batchDelete(commentses);
            for(Food advert:commentses){
                try{
                    FileUtils.forceDelete(new File(advert.getPicPath()));
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
