package com.elysiaptr.cemenghuiweb.authentication.handler.login;

import com.elysiaptr.cemenghuiweb.authentication.handler.login.username.UsernameAuthentication;
import com.elysiaptr.cemenghuiweb.web.po.Response;
import com.elysiaptr.cemenghuiweb.web.utils.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 认证成功/登录成功 事件处理器
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserLoginInfo userLoginInfo = ((UsernameAuthentication) authentication).getCurrentUser();
        request.getSession().setAttribute("userLoginInfo", userLoginInfo);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Login Success");
    }

}
