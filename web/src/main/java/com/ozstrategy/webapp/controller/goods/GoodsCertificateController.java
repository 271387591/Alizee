package com.ozstrategy.webapp.controller.goods;

import com.ozstrategy.model.commend.CommendType;
import com.ozstrategy.model.goods.GoodsCertificate;
import com.ozstrategy.model.goods.Merchant;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.goods.GoodsCertificateManager;
import com.ozstrategy.service.goods.MerchantManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.goods.GoodsCertificateCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-07-29.
*/
@RestController
@RequestMapping("goodsCertificate")
public class GoodsCertificateController extends BaseController {
    @Autowired
    private GoodsCertificateManager goodsCertificateManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private MerchantManager merchantManager;
    @RequestMapping("shop/list")
    public JsonReaderResponse<GoodsCertificateCommand> list(HttpServletRequest request){
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            Map<String,Object> objectMap=new HashMap<String, Object>();
            objectMap.put("Q_userId_EQ",user.getParentId());
            Merchant merchant=merchantManager.getByParam(objectMap);
            Map<String,Object> map=requestMap(request);
            map.put("merchantId",merchant.getId());
            List<Map<String,Object>> models= goodsCertificateManager.findByNamedQuery("getCerts", map, obtainStart(request), obtainLimit(request));
            Integer count=goodsCertificateManager.findByNamedQueryClass("getCertsCount",Integer.class,map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(null,false,"请求错误");
    }
    @RequestMapping("web/list")
    public JsonReaderResponse<GoodsCertificateCommand> weblist(HttpServletRequest request){
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            Map<String,Object> map=requestMap(request);
            map.put("userId",user.getId());
            map.put("Q_r.createDate","DESC");
            List<Map<String,Object>> models= goodsCertificateManager.findByNamedQuery("getUserCert", map, obtainStart(request), obtainLimit(request));
            Integer count=goodsCertificateManager.findByNamedQueryClass("getUserCertCount",Integer.class,map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(null,false,"请求错误");
    }



    @RequestMapping("shop/index")
    public ModelAndView activityList(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/goods/cert");
    }
    @RequestMapping("shop/edit/{id}")
    public JsonReaderSingleResponse<GoodsCertificateCommand> save(@PathVariable Long id,HttpServletRequest request){
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            GoodsCertificate goodsCertificate=goodsCertificateManager.get(id);
            goodsCertificate.setShoperId(user.getId());
            goodsCertificate.setShoperName(user.getNickName());
            goodsCertificate.setShoperMobile(user.getMobile());
            goodsCertificateManager.certPay(goodsCertificate);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<GoodsCertificateCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}
