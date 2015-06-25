package com.ozstrategy.webapp.controller.commend;

import com.ozstrategy.model.commend.Commend;
import com.ozstrategy.model.commend.CommendType;
import com.ozstrategy.model.system.ApplicationConfig;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.commend.CommendManager;
import com.ozstrategy.service.system.ApplicationConfigManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.commend.CommendCommand;
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
@RequestMapping("commend")
public class CommendController extends BaseController {
    @Autowired
    private CommendManager commendManager;
    @Autowired
    private UserManager userManager;

    @RequestMapping("listCount")
    public JsonReaderResponse<CommendCommand> list(HttpServletRequest request){
        List<CommendCommand> commands=new ArrayList<CommendCommand>();
        String itemId=request.getParameter("itemId");
        String type=request.getParameter("type");
        Map<String,Object> map=new HashMap<String, Object>();
        try{
            map.put("Q_typeId_EQ",CommendType.valueOf(type).ordinal());
            map.put("Q_itemId_EQ",parseLong(itemId));
            Integer count=commendManager.count(map);
            return new JsonReaderResponse(commands,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("web/save")
    public JsonReaderSingleResponse<CommendCommand> save(HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            if(StringUtils.isEmpty(username)){
                return new JsonReaderSingleResponse(null,false,"登录超时");
            }
            String itemId=request.getParameter("itemId");
            String type=request.getParameter("type");
            User user=userManager.getUserByUsername(username);
            Commend commend=new Commend();
            commend.setCreateDate(new Date());
            commend.setUserId(user.getId());
            commend.setTypeId(CommendType.valueOf(type).ordinal());
            commend.setItemId(parseLong(itemId));
            commendManager.save(commend);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("web/check")
    public JsonReaderSingleResponse<CommendCommand> check(HttpServletRequest request){
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

            Commend commend=commendManager.getByParam(map);
            if(commend!=null){
                return new JsonReaderSingleResponse(commend,true);
            }
            return new JsonReaderSingleResponse(commend,false);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"参数错误");
    }



    @RequestMapping("web/delete")
    public JsonReaderSingleResponse<CommendCommand> delete(HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            if(StringUtils.isEmpty(username)){
                return new JsonReaderSingleResponse(null,false,"登录超时");
            }
            String id=request.getParameter("id");
            commendManager.deleteById(parseLong(id));
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"参数错误");
    }
}
