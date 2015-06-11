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
    @Autowired
    private ApplicationConfigManager applicationConfigManager;
    @RequestMapping("home")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map=new HashMap<String, Object>();
//        List<ApplicationConfig> configs= applicationConfigManager.listAll();
//        if(configs!=null && configs.size()>0){
//            for(ApplicationConfig model:configs){
//                map.put(model.getSystemKey(),model.getSystemValue());
//            }
//        }
        return new ModelAndView("admin/home",map);
    }
    @RequestMapping("table")
    public ModelAndView tables(HttpServletRequest request, HttpServletResponse response) {

        return new ModelAndView("table/tables");
    }
}
