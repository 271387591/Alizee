package com.ozstrategy.webapp.controller;

import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.user.UserCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SimpleMVController implements InitializingBean {

    private final transient Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private UserManager userManager = null;

    public void afterPropertiesSet() throws Exception {
    }



    @RequestMapping("/desktopRes.js")
    public ModelAndView getGlobalRes(HttpServletRequest request, HttpServletResponse response) {

        UserCommand userCommand=new UserCommand();

        return new ModelAndView("res/desktopRes", "command", userCommand);
    }

}
