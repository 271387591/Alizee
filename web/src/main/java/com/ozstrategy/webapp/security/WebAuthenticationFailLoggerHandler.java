package com.ozstrategy.webapp.security;


import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.user.UserCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihao on 7/1/14.
 */
public class WebAuthenticationFailLoggerHandler extends WebAuthenticationLoggerHandler implements AuthenticationFailureHandler {
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String platform=request.getParameter("platform");
        if(StringUtils.equals(PLATFORM, platform)){
            response.sendRedirect("login?error=true");
            return;
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if(StringUtils.equals(request.getMethod(), "GET")){
            JsonReaderSingleResponse command=new JsonReaderSingleResponse(null,false,"请求方式错误，不支持GET请求");
            response.getWriter().print(objectMapper.writeValueAsString(command));
            return;
        }
        String result=objectMapper.writeValueAsString(new JsonReaderSingleResponse<UserCommand>(null,false,"用户名或密码错误"));
        response.getWriter().print(result);
        return;
        
    }
}
