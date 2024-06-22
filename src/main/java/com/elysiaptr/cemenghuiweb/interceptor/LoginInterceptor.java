package com.elysiaptr.cemenghuiweb.interceptor;

import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        logger.info("Intercepting request to: " + request.getRequestURI());
        logger.info("User session attribute: " + user);

        if (user == null) {
            logger.warn("Unauthorized access attempt to: " + request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置401未授权状态码
            response.getWriter().write("Unauthorized");
            return false;
        }
        return true;
    }
}
