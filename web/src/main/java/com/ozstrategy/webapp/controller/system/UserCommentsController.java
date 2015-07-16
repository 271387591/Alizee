package com.ozstrategy.webapp.controller.system;

import com.ozstrategy.model.system.Activity;
import com.ozstrategy.model.system.UserComments;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.system.UserCommentsManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.system.UserCommentsCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
* Created by lihao1 on 2015-07-14.
*/
@RestController
@RequestMapping("userComments")
public class UserCommentsController extends BaseController {
    @Autowired
    private UserCommentsManager userCommentsManager;
    @Autowired
    private UserManager userManager;

    @RequestMapping("list")
    public JsonReaderResponse list(HttpServletRequest request){
        List commands=new ArrayList();
        Map<String,Object> map=requestMap(request);
        try{
            commands=userCommentsManager.findByNamedQuery("getComments",map,obtainStart(request),obtainLimit(request));
            Integer count=userCommentsManager.findByNamedQueryClass("getCommentsCount",Integer.class,map);
            return new JsonReaderResponse(commands,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("web/list")
    public JsonReaderResponse weblist(HttpServletRequest request){
        List commands=new ArrayList();
        Map<String,Object> map=new HashMap<String, Object>();
        try{
            String username=request.getRemoteUser();
            User user=userManager.getUserByUsername(username);
            map.put("Q_userId_EQ",user.getId());
            commands=userCommentsManager.findByNamedQuery("getComments",map,obtainStart(request),obtainLimit(request));
            Integer count=userCommentsManager.findByNamedQueryClass("getCommentsCount",Integer.class,map);
            return new JsonReaderResponse(commands,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("security/detail/{id}")
    public ModelAndView userComments(@PathVariable Long id) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("id",id);
        Map<String,Object> objectMap=userCommentsManager.findByNamedQueryMap("getComment",map);

        return new ModelAndView("admin/system/commentsDetail","command",objectMap);
    }
    @RequestMapping("security/reply")
    public JsonReaderSingleResponse reply(HttpServletRequest request) {
        String ids=request.getParameter("ids");
        String reply=request.getParameter("reply");
        try{
            List<UserComments> commentses=new ArrayList<UserComments>();
            if(StringUtils.isNotEmpty(ids)){
                String[] ida=ids.split(",");
                for(String id:ida){
                    UserComments comments=userCommentsManager.get(parseLong(id));
                    comments.setLastUpdateDate(new Date());
                    comments.setReply(reply);
                    commentses.add(comments);
                }
            }
            userCommentsManager.batchUpdate(commentses);
            return new JsonReaderSingleResponse("");

        }catch (Exception e){

        }
        return new JsonReaderSingleResponse(null,false,"回复失败");
    }


    @RequestMapping("web/save")
    public JsonReaderSingleResponse<UserCommentsCommand> save(HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            String content=request.getParameter("content");
            String contract=request.getParameter("contract");
            User user=userManager.getUserByUsername(username);
            UserComments userComments=new UserComments();
            userComments.setUserId(user.getId());
            userComments.setContent(content);
            userComments.setContract(contract);
            userComments.setCreateDate(new Date());
            userCommentsManager.save(userComments);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("security/delete")
    public JsonReaderSingleResponse<UserCommentsCommand> delete(HttpServletRequest request){
        try{
            String ids=request.getParameter("ids");
            List<UserComments> commentses=new ArrayList<UserComments>();
            if(StringUtils.isNotEmpty(ids)){
                String[] ida=ids.split(",");
                for(String id:ida){
                    UserComments comments=userCommentsManager.get(parseLong(id));
                    commentses.add(comments);
                }
            }
            userCommentsManager.batchDelete(commentses);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
