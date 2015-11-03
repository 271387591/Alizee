package com.ozstrategy.webapp.controller.appstore;

import com.ozstrategy.Constants;
import com.ozstrategy.model.appstore.AppStore;
import com.ozstrategy.model.appstore.AppStorePlat;
import com.ozstrategy.model.system.Activity;
import com.ozstrategy.service.appstore.AppStoreManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.appstore.AppStoreCommand;
import com.ozstrategy.webapp.command.games.GameCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by lihao1 on 2015-08-30.
*/
@RestController
@RequestMapping("appStore")
public class AppStoreController extends BaseController {
    @Autowired
    private AppStoreManager appStoreManager;
    @RequestMapping("list")
    public JsonReaderResponse<AppStoreCommand> list(HttpServletRequest request){
        List<AppStoreCommand> commands=new ArrayList<AppStoreCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<AppStore> models= appStoreManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(AppStore model:models){
                        AppStoreCommand command=new AppStoreCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=appStoreManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("security/addApp")
    public ModelAndView addActivity(HttpServletRequest request){
        AppStoreCommand command=null;
        String url="admin/appstore/addAndroid";
        try{

            String id=request.getParameter("id");
            String plat=request.getParameter("plat");
            if(id==null){
                command=new AppStoreCommand();
                command.setEnabled(true);
            }else{
                command=new AppStoreCommand(appStoreManager.get(parseLong(id)));
            }
            if(parseInteger(plat)==AppStorePlat.IOS.ordinal()){
                url="admin/appstore/addIos";
            }
        }catch (Exception e){
            logger.error("addActivity fail",e);
        }
        return new ModelAndView(url,"command",command);
    }
    @RequestMapping(value = "security/upload")
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {
        String username    = request.getRemoteUser();
        String version        = request.getParameter("version");
        String enabled        = request.getParameter("enabled");
        String plat        = request.getParameter("plat");
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer = null;

        try {
            writer = response.getWriter();
        } catch (IOException e) { }

        if (writer == null) {
            return null;
        }

        AppStore advert=null;
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_version_EQ",version);
        map.put("Q_plat_EQ",0);
        if(StringUtils.isEmpty(id)){
            advert=appStoreManager.getByParam(map);
            if(advert!=null){
                writer.print("{\"success\":false,\"msg\":\"该版本已存在\"}");
                return null;
            }
            advert=new AppStore();
            advert.setCreateDate(new Date());
        }else{
            advert=appStoreManager.get(parseLong(id));
            AppStore advertn=appStoreManager.getByParam(map);
            if(advertn!=null && advertn.getId()!=advert.getId()){
                writer.print("{\"success\":false,\"msg\":\"该版本已存在\"}");
                return null;
            }
        }
        advert.setVersion(version);
        advert.setEnabled(BooleanUtils.toBoolean(enabled));
        advert.setPlat(AppStorePlat.valueOf(plat).ordinal());

        String attachFilesDirStr = request.getSession().getServletContext().getRealPath("/") + "/" + Constants.updloadAppstore + "/";
        attachFilesDirStr = FilenameUtils.normalize(attachFilesDirStr);



        File fileDir = new File(attachFilesDirStr);

        if (fileDir.exists() == false) {
            fileDir.mkdirs();
        }
        File fileOnServer = null;

        try {
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
                if(StringUtils.isEmpty(fileName))continue;
                ext         = FilenameUtils.getExtension(fileName);
                String attachFilesDir = attachFilesDirStr + "/" + str + "." + ext;
                attachFilesDir = FilenameUtils.normalize(attachFilesDir);
                fileOnServer      = new File(attachFilesDir);

                fileItem.write(fileOnServer);

                if(StringUtils.equals(controlName,"pacName")){
                    try{
                        FileUtils.forceDelete(new File(advert.getPacName()));
                    }catch (Exception e){
                    }
                    advert.setPacName(fileName);
                    advert.setPacPath(fileOnServer.getAbsolutePath());
                    String httpPath=toHttpUrl(request,true)+Constants.updloadAppstore+"/"+str+"."+ext;
                    advert.setPacUrl(httpPath);
                }
            }
            appStoreManager.saveApp(advert);
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



    @RequestMapping("security/saveios")
    public JsonReaderSingleResponse<AppStoreCommand> save(HttpServletRequest request){
        try{
            String iosUrl=request.getParameter("iosUrl");
            String version=request.getParameter("version");
            String enabled=request.getParameter("enabled");
            String plat        = request.getParameter("plat");
            String id=request.getParameter("id");
            AppStore appStore=null;Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_version_EQ",version);
            map.put("Q_plat_EQ",1);
            if(parseLong(id)!=null){
                appStore=appStoreManager.get(parseLong(id));
                AppStore appStoren=appStoreManager.getByParam(map);
                if(appStoren!=null && appStoren.getId()!=appStore.getId()){
                    return new JsonReaderSingleResponse<AppStoreCommand>(null,false,"该版本已存在");
                }
            }else{
                appStore=appStoreManager.getByParam(map);
                if(appStore!=null){
                    return new JsonReaderSingleResponse<AppStoreCommand>(null,false,"该版本已存在");
                }
                appStore=new AppStore();
                appStore.setCreateDate(new Date());
            }
            appStore.setVersion(version);
            appStore.setIosUrl(iosUrl);
            appStore.setPlat(AppStorePlat.valueOf(plat).ordinal());
            appStore.setEnabled(BooleanUtils.toBoolean(enabled));
            appStoreManager.saveApp(appStore);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("security/delete/{id}")
    public JsonReaderSingleResponse<AppStoreCommand> delete(@PathVariable Long id){
        try{
            appStoreManager.deleteById(id);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
    @RequestMapping("web/getlast")
    public JsonReaderSingleResponse<AppStoreCommand> getlast(HttpServletRequest request){
        try{
            String plat=request.getParameter("plat");
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_plat_EQ",plat);
            map.put("Q_enabled_EQ",1);
            AppStore appStore=appStoreManager.getByParam(map);
            if(appStore!=null){
                return new JsonReaderSingleResponse(new AppStoreCommand(appStore),true,"");
            }else{
                return new JsonReaderSingleResponse(null,true,"未上传版本");
            }
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"获取失败");
    }

}
