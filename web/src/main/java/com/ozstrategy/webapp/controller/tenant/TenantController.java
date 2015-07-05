package com.ozstrategy.webapp.controller.tenant;

import com.ozstrategy.model.goods.Merchant;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.goods.MerchantManager;
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
        return new ModelAndView("admin/goods/goods");
    }


}
