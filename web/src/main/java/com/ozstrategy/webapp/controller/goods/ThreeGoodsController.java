package com.ozstrategy.webapp.controller.goods;

import com.ozstrategy.model.goods.ThreeGoods;
import com.ozstrategy.model.system.Channel;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.goods.ThreeGoodsManager;
import com.ozstrategy.service.system.ChannelManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.util.ExcelUtils;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.goods.ThreeGoodsCommand;
import com.ozstrategy.webapp.controller.BaseController;
import com.ozstrategy.webapp.utils.ExportExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-08-31.
*/
@RestController
@RequestMapping("threeGoods")
public class ThreeGoodsController extends BaseController {
    @Autowired
    private ThreeGoodsManager threeGoodsManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private ChannelManager channelManager;

    @RequestMapping("list")
    public JsonReaderResponse list(HttpServletRequest request){
        List commands=new ArrayList();
            Map<String,Object> map=requestMap(request);
            try{
                List<Map<String,Object>> models= threeGoodsManager.findByNamedQuery("getGoods", map, obtainStart(request), obtainLimit(request));
                Integer count=threeGoodsManager.findByNamedQueryClass("getGoodsCount", Integer.class, map);
                return new JsonReaderResponse(models,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("security/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        ExcelUtils.Columns columns1=new ExcelUtils.Columns();
        ExcelUtils.Column r1_c0=new ExcelUtils.Column(0,"订单号",0,0,"orderNo",5000);
        ExcelUtils.Column r1_c1=new ExcelUtils.Column(1,"商品名称",0,0,"goodsName");
        ExcelUtils.Column r1_c2=new ExcelUtils.Column(2,"渠道名称",0,0,"channelName",5000);
        ExcelUtils.Column r1_c3=new ExcelUtils.Column(3,"渠道编号",0,0,"channelNo");
        ExcelUtils.Column r1_c4=new ExcelUtils.Column(4,"价格(游游币)",0,0,"goodsPrice",7000);
        ExcelUtils.Column r1_c5=new ExcelUtils.Column(5,"数量",0,0,"num",5000);
        ExcelUtils.Column r1_c6=new ExcelUtils.Column(6,"用户昵称",0,0,"nickName",5000);
        ExcelUtils.Column r1_c7=new ExcelUtils.Column(7,"用户手机",0,0,"mobile",5000);
        ExcelUtils.Column r1_c8=new ExcelUtils.Column(8,"出售时间",0,0,"createDate",5000);
        columns1.addColumn(r1_c0)
                .addColumn(r1_c1)
                .addColumn(r1_c2)
                .addColumn(r1_c3)
                .addColumn(r1_c4)
                .addColumn(r1_c5)
                .addColumn(r1_c6)
                .addColumn(r1_c7)
                .addColumn(r1_c8);
        String sheetName="第三方商品出售列表";
        ExcelUtils.Row row1=new ExcelUtils.Row(1,columns1);
        ExcelUtils.Rows rows=new ExcelUtils.Rows();
        rows.addRow(row1);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_t.createDate","DESC");
        List<Map<String,Object>> models= threeGoodsManager.findByNamedQuery("getGoods", map);
        ExportExcelUtils.export(sheetName, rows, sheetName, models, response, request);
    }
    @RequestMapping("web/save")
    public JsonReaderSingleResponse<ThreeGoodsCommand> save(HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            String channelNo=request.getParameter("channelNo");
            if(StringUtils.isEmpty(channelNo)){
                return new JsonReaderSingleResponse(null,false,"渠道号不能为空");
            }
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_channelNo_EQ",channelNo);
            Channel channel=channelManager.getByParam(map);
            if(channel==null){
                return new JsonReaderSingleResponse(null,false,"未找到相应渠道");
            }
            String goodsName=request.getParameter("goodsName");
            String num=request.getParameter("num");
            String goodsPrice=request.getParameter("goodsPrice");
            User user=userManager.getUserByUsername(username);
            Double credits=user.getCredits();
            if(credits<parseInteger(num)*parseDouble(goodsPrice)){
                return new JsonReaderSingleResponse(null,false,"用户余额不足");
            }
            ThreeGoods threeGoods=new ThreeGoods();
            threeGoods.setCreateDate(new Date());
            threeGoods.setChannelNo(channelNo);
            threeGoods.setChannelId(channel.getId());
            threeGoods.setChannelName(channel.getName());
            threeGoods.setGoodsName(goodsName);
            threeGoods.setGoodsPrice(parseDouble(goodsPrice));
            threeGoods.setNum(parseInteger(num));
            threeGoods.setUserId(user.getId());
            threeGoodsManager.sale(threeGoods,user);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"购买失败");
    }
    @RequestMapping("web/getusergoods")
    public JsonReaderResponse getusergoods(HttpServletRequest request){
        User user=userManager.getUserByUsername(request.getRemoteUser());
        Map<String,Object> map=requestMap(request);
        map.put("Q_createDate","DESC");
        map.put("Q_u.id_EQ",user.getId());
        List<Map<String,Object>> commands=new ArrayList<Map<String,Object>>();
        try{
            List<Map<String,Object>> models= threeGoodsManager.findByNamedQuery("getGoods", map, obtainStart(request), obtainLimit(request));
            Integer count=threeGoodsManager.findByNamedQueryClass("getGoodsCount", Integer.class, map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(commands,false,"请求错误");
    }
}
