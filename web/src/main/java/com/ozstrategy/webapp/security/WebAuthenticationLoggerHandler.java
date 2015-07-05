package com.ozstrategy.webapp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozstrategy.service.user.RoleManager;
import com.ozstrategy.service.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lihao on 12/17/14.
 */
public abstract class WebAuthenticationLoggerHandler {
    @Autowired
    protected UserManager userManager;
    @Autowired
    protected RoleManager roleManager;

    protected ObjectMapper objectMapper=new ObjectMapper();
    protected static String PLATFORM="PC";
    
    
}
