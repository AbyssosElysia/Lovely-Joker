package com.elysiaptr.cemenghuiweb.authentication.handler.login;

import com.elysiaptr.cemenghuiweb.web.po.Response;
import com.elysiaptr.cemenghuiweb.web.utils.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * AbstractAuthenticationProcessingFilter抛出AuthenticationException异常后，会跑到这里来
 */
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        Response responseData = Response.forbiddenResponse("login failed");
        writer.print(JSON.stringify(responseData));
        writer.flush();
        writer.close();
    }
}
