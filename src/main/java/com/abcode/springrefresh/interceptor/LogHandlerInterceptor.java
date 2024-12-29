package com.abcode.springrefresh.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class LogHandlerInterceptor  implements HandlerInterceptor {

    private static String USERNAME = "admin";
    private static String PASSWORD = "admin";

    private static final Logger logger = LoggerFactory.getLogger(LogHandlerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String reqHeader = request.getHeader("Authorization");
        if(StringUtils.hasText(reqHeader) && reqHeader.startsWith("Basic")){
            logger.info("Header included:::::::::::::::::: " + reqHeader);
            String base64Creds = reqHeader.substring("Basic ".length());
            byte[] decodedCreds = Base64.getDecoder().decode(base64Creds);
            String creds = new String(decodedCreds, StandardCharsets.UTF_8);
            String[] parts = creds.split(":");
            logger.info(parts[0]);
            logger.info(parts[1]);

            if (USERNAME.equals(parts[0]) && PASSWORD.equals(parts[1])){
                logger.info("User logged in successfully");
                return true;
            }


        }
        logger.info("LogHandlerInterceptor::preHandle");
        logger.info(request.getMethod() + " " + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("LogHandlerInterceptor::postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("LogHandlerInterceptor::afterCompletion");
    }
}
