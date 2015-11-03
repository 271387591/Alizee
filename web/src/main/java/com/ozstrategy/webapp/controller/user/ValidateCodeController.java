package com.ozstrategy.webapp.controller.user;

import com.ozstrategy.model.system.Channel;
import com.ozstrategy.model.user.User;
import com.ozstrategy.model.user.ValidateCode;
import com.ozstrategy.model.user.ValidateCodeType;
import com.ozstrategy.service.system.ChannelManager;
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
import java.util.HashMap;
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
    @Autowired
    private ChannelManager channelManager;


    @RequestMapping("getCode")
    public JsonReaderSingleResponse<ValidateCodeCommand> save(HttpServletRequest request){
        try{
            String channel=obtain(request,"channel");
            if(StringUtils.isEmpty(channel)){
                return new JsonReaderSingleResponse(null,false,"渠道不能为空");
            }
            Map<String,Object> cmap=new HashMap<String, Object>();
            cmap.put("Q_channelNo_EQ",channel);

            Channel channel1=channelManager.getByParam(cmap);
            if(channel1==null){
                return new JsonReaderSingleResponse(null,false,"未找到该渠道");
            }
            String mobile=request.getParameter("mobile");
            String type=request.getParameter("type");
            if(StringUtils.isEmpty(mobile)){
                return new JsonReaderSingleResponse(null,false,"请填写手机号");
            }
            ValidateCodeType validateCodeType=ValidateCodeType.valueOf(type);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_enabled_EQ",Boolean.TRUE);
            map.put("Q_mobile_EQ",mobile);
            User user=userManager.getByParam(map);

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
    @RequestMapping("getCode1")
    public JsonReaderSingleResponse<ValidateCodeCommand> save1(HttpServletRequest request){
        try{
            String channel=obtain(request,"channel");
            if(StringUtils.isEmpty(channel)){
                return new JsonReaderSingleResponse(null,false,"渠道不能为空");
            }
            Map<String,Object> cmap=new HashMap<String, Object>();
            cmap.put("Q_channelNo_EQ",channel);

            Channel channel1=channelManager.getByParam(cmap);
            if(channel1==null){
                return new JsonReaderSingleResponse(null,false,"未找到该渠道");
            }
            String mobile=request.getParameter("mobile");
            String type=request.getParameter("type");
            if(StringUtils.isEmpty(mobile)){
                return new JsonReaderSingleResponse(null,false,"请填写手机号");
            }
            ValidateCodeType validateCodeType=ValidateCodeType.valueOf(type);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_enabled_EQ",Boolean.TRUE);
            map.put("Q_mobile_EQ",mobile);
            User user=userManager.getByParam(map);

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
