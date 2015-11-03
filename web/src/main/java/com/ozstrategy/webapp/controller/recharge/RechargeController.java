package com.ozstrategy.webapp.controller.recharge;

import com.ozstrategy.model.recharge.Recharge;
import com.ozstrategy.model.recharge.RechargeStatus;
import com.ozstrategy.model.system.Channel;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.recharge.RechargeManager;
import com.ozstrategy.service.system.ChannelManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.util.ExcelUtils;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.recharge.RechargeCommand;
import com.ozstrategy.webapp.controller.BaseController;
import com.ozstrategy.webapp.utils.ExportExcelUtils;
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
    @Autowired
    private ChannelManager channelManager;


    @RequestMapping("security/list")
    public JsonReaderResponse list(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            map.put("Q_a.createDate","DESC");
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
    @RequestMapping("security/exportExcel/{status}")
    public void exportExcel(@PathVariable Integer status,HttpServletRequest request, HttpServletResponse response) {
        ExcelUtils.Columns columns1=new ExcelUtils.Columns();
        ExcelUtils.Column r1_c0=new ExcelUtils.Column(0,"充值编号",0,0,"rechargeNo",5000);
        ExcelUtils.Column r1_c1=new ExcelUtils.Column(1,"用户手机",0,0,"mobile");
        ExcelUtils.Column r1_c2=new ExcelUtils.Column(2,"用户昵称",0,0,"nickName",5000);
        ExcelUtils.Column r1_c3=new ExcelUtils.Column(3,"充值金额(元)",0,0,"money");
        ExcelUtils.Column r1_c4=new ExcelUtils.Column(4,"充值时间",0,0,"createDate",5000);
        ExcelUtils.Column r1_c5=new ExcelUtils.Column(5,"失败信息",0,0,"details",5000);
        columns1.addColumn(r1_c0)
                .addColumn(r1_c1)
                .addColumn(r1_c2)
                .addColumn(r1_c3)
                .addColumn(r1_c4);
        String sheetName="充值成功列表";
        if(status==RechargeStatus.Fail.ordinal()){
            columns1.addColumn(r1_c5);
            sheetName="充值失败列表";
        }
        ExcelUtils.Row row1=new ExcelUtils.Row(1,columns1);
        ExcelUtils.Rows rows=new ExcelUtils.Rows();
        rows.addRow(row1);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_a.createDate","DESC");
        map.put("Q_a.status_EQ",status);
        List<Map<String,Object>> list= rechargeManager.findByNamedQuery("getRecharges", map);
        ExportExcelUtils.export(sheetName,rows,sheetName,list,response,request);
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
        String channel=obtain(request,"channel");
        if(StringUtils.isEmpty(channel)){
            map.put("success",false);
            map.put("message","渠道不能为空");
            return map;
        }
        Map<String,Object> cmap=new HashMap<String, Object>();
        cmap.put("Q_channelNo_EQ",channel);

        Channel channel1=channelManager.getByParam(cmap);
        if(channel1==null){
            map.put("success",false);
            map.put("message","未找到该渠道");
            return map;
        }
        try{
            String detail=request.getParameter("detail");
            if(StringUtils.isEmpty(detail) && channel1.getType()!=1){
                map.put("success",false);
                map.put("message","商品描述不能为空");
                return map;
            }
            String money=request.getParameter("money");
            String credits=request.getParameter("credits");
            User user=userManager.getUserByUsername(username);
            Recharge recharge=new Recharge();
            recharge.setChannelDetail(detail);
            recharge.setCreateDate(new Date());
            recharge.setStatus(RechargeStatus.NoPay.ordinal());
            recharge.setUserId(user.getId());
            recharge.setMoney(parseDouble(money));
            recharge.setCredits(parseDouble(money)*100);
            recharge.setChannel(channel1.getName());
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

    @RequestMapping("web/getRecharge")
    public JsonReaderResponse getRecharge(HttpServletRequest request){
        String channel=obtain(request,"channel");
        if(StringUtils.isEmpty(channel)){
            return new JsonReaderResponse(null,false,"渠道不能为空");
        }
        Map<String,Object> cmap=new HashMap<String, Object>();
        cmap.put("Q_channelNo_EQ",channel);

        Channel channel1=channelManager.getByParam(cmap);
        if(channel1==null){
            new JsonReaderResponse(null,false,"未找到该渠道");
        }
        String username=request.getRemoteUser();
        User user=userManager.getUserByUsername(username);
        Map<String,Object> map=requestMap(request);
        try{
            map.put("Q_a.userId_EQ",user.getId());
            map.put("Q_a.createDate","DESC");
            List<Map<String,Object>> models= rechargeManager.findByNamedQuery("getRecharges", map, obtainStart(request), obtainLimit(request));
            Integer count=rechargeManager.findByNamedQueryClass("getRechargesCount",Integer.class,map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(null,false,"请求错误");
    }

}
