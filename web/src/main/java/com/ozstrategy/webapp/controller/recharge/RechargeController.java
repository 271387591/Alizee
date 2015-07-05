package com.ozstrategy.webapp.controller.recharge;

import com.ozstrategy.model.recharge.Recharge;
import com.ozstrategy.model.recharge.RechargeStatus;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.recharge.RechargeManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.recharge.RechargeCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
* Created by lihao1 on 2015-07-02.
*/
@RestController
@RequestMapping("recharge")
public class RechargeController extends BaseController {
    @Autowired
    private RechargeManager rechargeManager;
    @Autowired
    private UserManager userManager;

    @RequestMapping("list")
    public JsonReaderResponse<RechargeCommand> list(HttpServletRequest request){
        List<RechargeCommand> commands=new ArrayList<RechargeCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<Recharge> models= rechargeManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(Recharge model:models){
                        RechargeCommand command=new RechargeCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=rechargeManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<RechargeCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<RechargeCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
    @RequestMapping("web/createOrder")
    public Map<String,Object> createOrder(HttpServletRequest request) {
        Map<String,Object> map=new HashMap<String, Object>();
        String username=request.getRemoteUser();
        if(StringUtils.isEmpty(username)){
            map.put("success",false);
            map.put("message","登录超时");
            return map;
        }
        try{
            String money=request.getParameter("money");
            String credits=request.getParameter("credits");
            User user=userManager.getUserByUsername(username);
            Recharge recharge=new Recharge();
            recharge.setCreateDate(new Date());
            recharge.setStatus(RechargeStatus.NoPay.ordinal());
            recharge.setUserId(user.getId());
            recharge.setMoney(parseDouble(money));
            recharge.setCredits(parseDouble(credits));
            String data=rechargeManager.createOrder(recharge);
            map.put("success", true);
            map.put("message", "");
            map.put("data", data);
            return map;
        }catch (Exception e){
        }
        map.put("success",false);
        map.put("message","提交订单失败");
        return map;
    }

}
