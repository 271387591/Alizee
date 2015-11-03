package com.ozstrategy.webapp.controller.security;

import com.ozstrategy.model.system.ApplicationConfig;
import com.ozstrategy.service.system.ApplicationConfigManager;
import com.ozstrategy.webapp.command.system.ApplicationConfigCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihao1 on 6/10/15.
 */
@RestController
@RequestMapping("security")
public class SecurityController extends BaseController{
    @RequestMapping("about")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/system/about");
    }
    @RequestMapping("advert")
    public ModelAndView advert(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/system/advert");
    }
    @RequestMapping("food")
    public ModelAndView food(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/system/food");
    }
    @RequestMapping("activity")
    public ModelAndView activity(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/system/activity");
    }
    @RequestMapping("userComments")
    public ModelAndView userComments(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/system/usercomments");
    }
    @RequestMapping("notice")
    public ModelAndView notice(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/system/notice");
    }


    @RequestMapping("games")
    public ModelAndView games(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/games/game");
    }
    @RequestMapping("user")
    public ModelAndView tables(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/user/user");
    }
    @RequestMapping("recharge")
    public ModelAndView recharge(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/recharge/recharge");
    }
    @RequestMapping("purchasegoods")
    public ModelAndView purchasegoods(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/goods/purchasegoods");
    }




    @RequestMapping("mobilenotice")
    public ModelAndView mobilenotice(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/mobilenotice");
    }
    @RequestMapping("goodssale")
    public ModelAndView goodssaleList(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/goods/admingoodssale");
    }
    @RequestMapping("creditsDetail")
    public ModelAndView creditDetail(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/user/creditsDetail");
    }

    @RequestMapping("channel")
    public ModelAndView channel(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/system/channel");
    }
    @RequestMapping("appstore")
    public ModelAndView appstore(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/appstore/appstore");
    }
    @RequestMapping("threegoodssale")
    public ModelAndView threegoodssale(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/goods/threegoodssale");
    }







}
