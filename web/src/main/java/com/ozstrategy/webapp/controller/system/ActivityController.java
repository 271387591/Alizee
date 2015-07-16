package com.ozstrategy.webapp.controller.system;

import com.ozstrategy.Constants;
import com.ozstrategy.model.commend.CommendType;
import com.ozstrategy.model.system.*;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.commend.CommendManager;
import com.ozstrategy.service.commend.CommentManager;
import com.ozstrategy.service.system.ActivityManager;
import com.ozstrategy.service.system.ActivityUserManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.system.ActivityCommand;
import com.ozstrategy.webapp.command.system.ActivityUserCommand;
import com.ozstrategy.webapp.command.system.AdvertCommand;
import com.ozstrategy.webapp.command.user.UserCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
import java.util.*;

/**
* Created by lihao1 on 2015-06-25.
*/
@RestController
@RequestMapping("activity")
public class ActivityController extends BaseController {
    @Autowired
    private ActivityManager activityManager;
    @Autowired
    private ActivityUserManager activityUserManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private CommentManager commentManager;
    @Autowired
    private CommendManager commendManager;


    @RequestMapping("list")
    public JsonReaderResponse<ActivityCommand> list(HttpServletRequest request){
        List<ActivityCommand> commands=new ArrayList<ActivityCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<Activity> models= activityManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(Activity model:models){
                        ActivityCommand command=new ActivityCommand(model);
                        if(command.getPublished()){
                            Map<String,Object> usermap=new HashMap<String, Object>();
                            usermap.put("Q_activityId_EQ",command.getId());
                            Integer peoples=activityUserManager.count(usermap);
                            command.setPeoples(peoples);
                        }
                        Map<String,Object> objectMap=new HashMap<String, Object>();
                        objectMap.put("Q_itemId_EQ",model.getId());
                        objectMap.put("Q_typeId_EQ", CommendType.Activity.ordinal());
                        Integer comment=commentManager.count(objectMap);
                        Integer commend=commendManager.count(objectMap);
                        command.setComment(comment);
                        command.setCommend(commend);
                        commands.add(command);
                    }
                }
                Integer count=activityManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("security/activityList")
    public ModelAndView activityList(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_published_EQ",1);
        Integer count=activityManager.count(map);
        map=new HashMap<String, Object>();
        map.put("count",count==null?0:count);
        return new ModelAndView("admin/system/activityList","command",map);
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<ActivityCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("security/addActivity")
    public ModelAndView addActivity(HttpServletRequest request){
        ActivityCommand command=null;
        try{

            String id=request.getParameter("id");
            if(id==null){
                command=new ActivityCommand();
            }else{
                command=new ActivityCommand(activityManager.get(parseLong(id)));
            }
        }catch (Exception e){
            logger.error("addActivity fail",e);
        }
        return new ModelAndView("admin/system/addActivity","command",command);
    }
    @RequestMapping("security/activityDetail")
    public ModelAndView activityDetail(HttpServletRequest request){
        ActivityCommand command=null;
        try{

            String id=request.getParameter("id");
            if(id==null){
                command=new ActivityCommand();
            }else{
                command=new ActivityCommand(activityManager.get(parseLong(id)));
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("Q_activityId_EQ",command.getId());
                Integer peoples=activityUserManager.count(map);
                command.setPeoples(peoples);
            }
        }catch (Exception e){
            logger.error("activityDetail fail",e);
        }
        return new ModelAndView("admin/system/activityDetail","command",command);
    }

    @RequestMapping(value = "security/upload")
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {
        String username    = request.getRemoteUser();
        String title        = request.getParameter("title");
        String description = request.getParameter("description");
        String id = request.getParameter("id");
        String merchant = request.getParameter("merchant");
        String merchantPhone = request.getParameter("merchantPhone");
        String merchantAddress = request.getParameter("merchantAddress");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String publish = request.getParameter("publish");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer = null;

        try {
            writer = response.getWriter();
        } catch (IOException e) { }

        if (writer == null) {
            return null;
        }



        String attachFilesDirStr = request.getSession().getServletContext().getRealPath("/") + "/" + Constants.updloadActivity + "/";
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
                Activity advert=null;
                if(StringUtils.isEmpty(id)){
                    advert=new Activity();
                    advert.setCreateDate(new Date());
                }else{
                    advert=activityManager.get(parseLong(id));

                }
                advert.setTitle(title);
                advert.setDescription(description);
                advert.setMerchant(merchant);
                advert.setMerchantPhone(merchantPhone);
                advert.setMerchantAddress(merchantAddress);
                advert.setStartDate(DateUtils.parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
                advert.setEndDate(DateUtils.parseDate(endDate, "yyyy-MM-dd HH:mm:ss"));
                advert.setPublished(BooleanUtils.toBoolean(publish));
                if(advert.getPublished()){
                    advert.setLastUpdateDate(new Date());
                }
                if(StringUtils.isNotEmpty(fileName)){
                    try{
                        FileUtils.forceDelete(new File(advert.getPicPath()));
                    }catch (Exception e){
                    }
                    advert.setPicName(fileName);
                    advert.setPicPath(fileOnServer.getAbsolutePath());
                    String httpPath=toHttpUrl(request,true)+Constants.updloadActivity+"/"+str+"."+ext;
                    advert.setUrl(httpPath);
                }
                activityManager.saveOrUpdate(advert);
                writer.print("{\"success\":true,\"msg\":\"\"}");
            }

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
            List<Activity> commentses=new ArrayList<Activity>();
            if(StringUtils.isNotEmpty(ids)){
                String[] ida=ids.split(",");
                for(String id:ida){
                    Activity comments=activityManager.get(parseLong(id));
                    commentses.add(comments);
                }
            }
            activityManager.deleteAct(commentses);
            for(Activity advert:commentses){
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
    @RequestMapping("security/publish/{id}")
    public JsonReaderSingleResponse<ActivityCommand> publish(@PathVariable Long id){
        try{
            Activity activity=activityManager.get(id);
            if(activity!=null){
                activity.setPublished(true);
                activity.setLastUpdateDate(new Date());
                activityManager.update(activity);
            }
            return new JsonReaderSingleResponse("",Boolean.TRUE);
        }catch (Exception e){
            logger.error("publish fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"发布失败");
    }
    @RequestMapping("security/endActivity/{id}")
    public JsonReaderSingleResponse<ActivityCommand> endActivity(@PathVariable Long id){
        try{
            Activity activity=activityManager.get(id);
            if(activity!=null){
                activity.setEndDate(new Date());
                activityManager.update(activity);
            }
            return new JsonReaderSingleResponse("",Boolean.TRUE);
        }catch (Exception e){
            logger.error("publish fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"发布失败");
    }
    @RequestMapping("security/pendding/{id}")
    public JsonReaderSingleResponse<ActivityCommand> pendding(@PathVariable Long id){
        try{
            ActivityUser activityUser=activityUserManager.get(id);
            if(activityUser!=null){
                activityUser.setStatus(ActivityUserStatus.CompletePending.ordinal());
                activityUserManager.update(activityUser);
            }
            return new JsonReaderSingleResponse("",Boolean.TRUE);
        }catch (Exception e){
            logger.error("pendding fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"审核失败");
    }
    @RequestMapping("security/nopendding/{id}")
    public JsonReaderSingleResponse<ActivityCommand> nopendding(@PathVariable Long id){
        try{
            ActivityUser activityUser=activityUserManager.get(id);
            if(activityUser!=null){
                activityUser.setStatus(ActivityUserStatus.CheckPending.ordinal());
                activityUserManager.update(activityUser);
            }
            return new JsonReaderSingleResponse("",Boolean.TRUE);
        }catch (Exception e){
            logger.error("nopendding fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"取消失败");
    }


    @RequestMapping("web/joinActivity")
    public JsonReaderSingleResponse<ActivityCommand> joinActivity(HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            if(org.apache.commons.lang.StringUtils.isEmpty(username)){
                return new JsonReaderSingleResponse(null,false,"登录超时");
            }
            User user=userManager.getUserByUsername(username);
            String id=obtain(request,"id");
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_userId_EQ",user.getId());
            map.put("Q_activityId_EQ",parseLong(id));
            ActivityUser activityUser=activityUserManager.getByParam(map);
            if(activityUser!=null){
                return new JsonReaderSingleResponse(null,false,"该用户已参加活动");
            }
            activityUser=new ActivityUser();
            activityUser.setCreateDate(new Date());
            activityUser.setActivityId(parseLong(id));
            activityUser.setUserId(user.getId());
            activityUserManager.save(activityUser);
            return new JsonReaderSingleResponse("",Boolean.TRUE);
        }catch (Exception e){
            logger.error("joinActivity fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"加入失败");
    }
    @RequestMapping("web/checkJoinActivity")
    public JsonReaderSingleResponse<ActivityCommand> checkJoinActivity(HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            if(org.apache.commons.lang.StringUtils.isEmpty(username)){
                return new JsonReaderSingleResponse(null,false,"登录超时");
            }
            User user=userManager.getUserByUsername(username);
            String id=obtain(request,"id");
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_userId_EQ",user.getId());
            map.put("Q_activityId_EQ",parseLong(id));
            ActivityUser activityUser=activityUserManager.getByParam(map);
            if(activityUser==null){
                return new JsonReaderSingleResponse(null,true,"该用户未参加活动");
            }else{
                return new JsonReaderSingleResponse(new ActivityUserCommand(activityUser),true,"该用户已参加活动");
            }
        }catch (Exception e){
            logger.error("joinActivity fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"查询失败");
    }
    @RequestMapping("web/logoutActivity")
    public JsonReaderSingleResponse<ActivityCommand> logoutActivity(HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            if(org.apache.commons.lang.StringUtils.isEmpty(username)){
                return new JsonReaderSingleResponse(null,false,"登录超时");
            }
            User user=userManager.getUserByUsername(username);
            String id=obtain(request,"id");
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_userId_EQ",user.getId());
            map.put("Q_activityId_EQ",parseLong(id));
            ActivityUser activityUser=activityUserManager.getByParam(map);
            if(activityUser==null){
                return new JsonReaderSingleResponse(null,false,"该用户未参加活动");
            }else{
                activityUserManager.delete(activityUser);
                return new JsonReaderSingleResponse(null,true,"");
            }
        }catch (Exception e){
            logger.error("logoutActivity fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"退出失败");
    }
    @RequestMapping("web/getActivityUsers")
    public JsonReaderResponse getActivityUsers(HttpServletRequest request){
        try{
            String id=obtain(request,"id");
            Map<String,Object> map=requestMap(request);
            map.put("activityId",parseLong(id));
            map.put("Q_a.createDate","DESC");
            List<Map<String,Object>> users=activityUserManager.findByNamedQuery("getUsers", map, obtainStart(request), obtainLimit(request));

            Integer count=activityUserManager.findByNamedQueryClass("getUsersCount",Integer.class,map);
            return new JsonReaderResponse(users,true,count,"");
        }catch (Exception e){
            logger.error("logoutActivity fail",e);
        }
        return new JsonReaderResponse(null,false,0,"");
    }






}
