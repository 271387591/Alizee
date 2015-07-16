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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("security/list")
    public JsonReaderResponse list(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            List<Map<String,Object>> models= rechargeManager.findByNamedQuery("getRecharges", map, obtainStart(request), obtainLimit(request));
            Integer count=rechargeManager.findByNamedQueryClass("getRechargesCount",Integer.class,map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(null,false,"请求错误");
    }
    @RequestMapping("security/rechargeList")
    public ModelAndView activityList(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map=new HashMap<String, Object>();
        return new ModelAndView("admin/recharge/rechargeList","command",map);
    }
    @RequestMapping("security/detail/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("id",id);
        Map<String,Object> command=rechargeManager.findByNamedQueryMap("getRecharge",map);
        return new ModelAndView("admin/recharge/rechargeDetail","command",command);
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
