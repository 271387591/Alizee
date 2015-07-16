package com.ozstrategy.webapp.controller.commend;

import com.ozstrategy.model.commend.Commend;
import com.ozstrategy.model.commend.CommendType;
import com.ozstrategy.model.commend.Comment;
import com.ozstrategy.model.system.ApplicationConfig;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.commend.CommentManager;
import com.ozstrategy.service.system.ApplicationConfigManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.commend.CommendCommand;
import com.ozstrategy.webapp.command.commend.CommentCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
* Created by lihao1 on 2015-06-18.
*/
@RestController
@RequestMapping("comment")
public class CommentController extends BaseController {
    @Autowired
    private CommentManager commentManager;
    @Autowired
    private UserManager userManager;
    @RequestMapping("list")
    public JsonReaderResponse list(HttpServletRequest request){
        String itemId=request.getParameter("itemId");
        String type=request.getParameter("type");
        Map<String,Object> map=new HashMap<String, Object>();
        try{
            map.put("typeId",CommendType.valueOf(type).ordinal());
            map.put("itemId",parseLong(itemId));
            List<Map<String,Object>> models= commentManager.findByNamedQuery("getComments", map, obtainStart(request), obtainLimit(request));
            Integer count=commentManager.findByNamedQueryClass("getCommentsCount",Integer.class,map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(null,false,"请求错误");
    }
    @RequestMapping("web/save")
    public JsonReaderSingleResponse<CommentCommand> save(HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            if(StringUtils.isEmpty(username)){
                return new JsonReaderSingleResponse(null,false,"登录超时");
            }
            String itemId=request.getParameter("itemId");
            String type=request.getParameter("type");
            String rank=request.getParameter("rank");
            String content=request.getParameter("content");
            User user=userManager.getUserByUsername(username);
            Comment comment=new Comment();
            comment.setCreateDate(new Date());
            comment.setItemId(parseLong(itemId));
            comment.setTypeId(CommendType.valueOf(type).ordinal());
            comment.setUserId(user.getId());
            comment.setRank(parseInteger(rank));
            comment.setContent(content);
            commentManager.save(comment);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("web/check")
    public JsonReaderSingleResponse<CommentCommand> check(HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            if(StringUtils.isEmpty(username)){
                return new JsonReaderSingleResponse(null,false,"登录超时");
            }
            String itemId=request.getParameter("itemId");
            String type=request.getParameter("type");
            User user=userManager.getUserByUsername(username);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_userId_EQ",user.getId());
            map.put("Q_itemId_EQ",parseLong(itemId));
            map.put("Q_typeId_EQ",CommendType.valueOf(type).ordinal());

            Comment comment=commentManager.getByParam(map);
            if(comment!=null){
                return new JsonReaderSingleResponse(comment,true);
            }
            return new JsonReaderSingleResponse(comment,false);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"参数错误");
    }
    @RequestMapping("web/delete")
    public JsonReaderSingleResponse<CommentCommand> delete(HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            if(StringUtils.isEmpty(username)){
                return new JsonReaderSingleResponse(null,false,"登录超时");
            }
            String id=request.getParameter("id");
            commentManager.deleteById(parseLong(id));
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
