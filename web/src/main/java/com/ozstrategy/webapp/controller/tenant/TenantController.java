package com.ozstrategy.webapp.controller.tenant;

import com.ozstrategy.model.goods.Merchant;
import com.ozstrategy.model.system.Certificate;
import com.ozstrategy.model.system.GoodsSetting;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.goods.GoodsCertificateManager;
import com.ozstrategy.service.goods.MerchantManager;
import com.ozstrategy.service.system.CertificateManager;
import com.ozstrategy.service.system.GoodsSettingManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.goods.MerchantCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihao1 on 6/30/15.
 */

@RestController
@RequestMapping("tenant")
public class TenantController extends BaseController{
    @Autowired
    private MerchantManager merchantManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private GoodsSettingManager goodsSettingManager;
    @Autowired
    private CertificateManager certificateManager;



    @RequestMapping("merchantinfo")
    public ModelAndView home(HttpServletRequest request) {
        String username=request.getRemoteUser();
        User user=userManager.getUserByUsername(username);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_userId_EQ",user.getId());
        Merchant merchant=merchantManager.getByParam(map);
        MerchantCommand command=null;
        if(merchant!=null){
            command=new MerchantCommand(merchant);
        }else{
            command=new MerchantCommand();
        }
        return new ModelAndView("admin/goods/merchantInfo","command",command);
    }
    @RequestMapping("goods")
    public ModelAndView goods(HttpServletRequest request) {
        List<GoodsSetting> models= goodsSettingManager.list(new HashMap<String, Object>(), 0, 1000);
        Map<String,Object> map=new HashMap<String, Object>();
        if(models!=null && models.size()>0){
            Integer type=models.get(0).getType();
            if(type==1){
                map.put("type",models.get(0).getType());
                map.put("startDate",models.get(0).getStartDate());
                map.put("endDate",models.get(0).getEndDate());
            }else{
                map.put("type",type);
                map.put("goods",models);
            }

        }
        List<Certificate> certificates=certificateManager.listAll();
        if(certificates!=null && certificates.size()>0){
            map.put("certificate",certificates.get(0));
        }
        return new ModelAndView("admin/goods/goods","command",map);
    }
    @RequestMapping("goodssale")
    public ModelAndView goodssale(HttpServletRequest request) {
        return new ModelAndView("admin/goods/goodssale");
    }



}
