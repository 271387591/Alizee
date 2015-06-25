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


    @RequestMapping("games")
    public ModelAndView games(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("admin/games");
    }
    @RequestMapping("user")
    public ModelAndView tables(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("admin/user/user");
    }


}
