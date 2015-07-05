package com.ozstrategy.webapp.controller.user;

import com.ozstrategy.model.user.User;
import com.ozstrategy.model.user.ValidateCode;
import com.ozstrategy.model.user.ValidateCodeType;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.service.user.ValidateCodeManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.user.ValidateCodeCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-06-10.
*/
@RestController
@RequestMapping("validateCode")
public class ValidateCodeController extends BaseController {
    @Autowired
    private ValidateCodeManager validateCodeManager;
    @Autowired
    private UserManager userManager;

    @RequestMapping("getCode")
    public JsonReaderSingleResponse<ValidateCodeCommand> save(HttpServletRequest request){
        try{
            String mobile=request.getParameter("mobile");
            String type=request.getParameter("type");
            if(StringUtils.isEmpty(mobile)){
                return new JsonReaderSingleResponse(null,false,"请填写手机号");
            }
            ValidateCodeType validateCodeType=ValidateCodeType.valueOf(type);
            User user=userManager.getUserByUsername(mobile);
            if(validateCodeType==ValidateCodeType.GetBackPwd){
                if(user==null){
                    return new JsonReaderSingleResponse(null,false,"该用户不存在");
                }
            }else if(validateCodeType==ValidateCodeType.Register){
                if(user!=null){
                    return new JsonReaderSingleResponse(null,false,"该用户已注册");
                }
            }
            boolean ret = validateCodeManager.sendCode(mobile, validateCodeType);
            if(ret){
                return new JsonReaderSingleResponse(ret);
            }
            return new JsonReaderSingleResponse(ret,false,"获取短信失败");
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"参数错误");
    }
}
