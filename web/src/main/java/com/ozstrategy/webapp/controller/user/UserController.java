package com.ozstrategy.webapp.controller.user;

import com.ozstrategy.model.user.User;
import com.ozstrategy.model.user.ValidateCode;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.service.user.ValidateCodeManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.user.UserCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
* Created by lihao1 on 2015-06-08.
*/
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private UserManager userManager;
    @Autowired
    private ValidateCodeManager validateCodeManager;

    @RequestMapping("list")
    public JsonReaderResponse<UserCommand> list(HttpServletRequest request){
        List<UserCommand> commands=new ArrayList<UserCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<User> models= userManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(User model:models){
                        UserCommand command=new UserCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=userManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<UserCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<UserCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
    @RequestMapping("register")
    public JsonReaderSingleResponse<UserCommand> register(HttpServletRequest request){
        try{
            String mobile=obtain(request,"mobile");
            String password=obtain(request,"password");
            String nickName=obtain(request,"nickName");
            String validateCode=obtain(request,"validateCode");
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_mobile_EQ",mobile);
            map.put("Q_loseDate_GE",new Date());
            ValidateCode validate=validateCodeManager.getByParam(map);
            if(validate==null || !StringUtils.equals(validateCode,validate.getCode())){
                return new JsonReaderSingleResponse<UserCommand>(null,false,"验证码已过期");
            }
            User user=new User();
            user.setMobile(mobile);
            user.setNickName(nickName);
            user.setUsername(mobile);
            user.setPassword(password);
            userManager.saveUser(user);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }


}
