package com.ozstrategy.webapp.controller.goods;

import com.ozstrategy.model.goods.GoodsCertificate;
import com.ozstrategy.model.goods.GoodsOrder;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.goods.GoodsCertificateManager;
import com.ozstrategy.service.goods.GoodsOrderManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.goods.GoodsOrderCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-08-04.
*/
@RestController
@RequestMapping("goodsOrder")
public class GoodsOrderController extends BaseController {
    @Autowired
    private GoodsOrderManager goodsOrderManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private GoodsCertificateManager goodsCertificateManager;


    @RequestMapping("web/getGoodsOrder")
    public JsonReaderResponse<GoodsOrderCommand> list(HttpServletRequest request){
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            Map<String,Object> map=requestMap(request);
            map.put("Q_r.userId_EQ",user.getId());
            map.put("Q_r.createDate","DESC");
            List<Map<String,Object>> list=goodsOrderManager.findByNamedQuery("getGoodsOrders",map,obtainStart(request),obtainLimit(request));
            Integer count=goodsOrderManager.findByNamedQueryClass("getGoodsOrdersCount",Integer.class,map);
            if(list!=null && list.size()>0){
                for(Map<String,Object> objectMap:list){
                    map=new HashMap<String, Object>();
                    map.put("Q_orderId_EQ",objectMap.get("id"));
                    List<GoodsCertificate> certs=goodsCertificateManager.listAll(map);
                    objectMap.put("certs",certs);
                }
            }
            return new JsonReaderResponse(list,true,count,"");

        }catch (Exception e){

        }
        return new JsonReaderResponse(new ArrayList(),false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<GoodsOrderCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<GoodsOrderCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
