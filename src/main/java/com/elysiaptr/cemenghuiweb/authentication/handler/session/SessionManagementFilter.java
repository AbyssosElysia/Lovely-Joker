package com.elysiaptr.cemenghuiweb.authentication.handler.session;

import com.elysiaptr.cemenghuiweb.authentication.handler.login.UserLoginInfo;
import com.elysiaptr.cemenghuiweb.authentication.handler.login.username.UsernameAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SessionManagementFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null && request.getSession(false) != null) {
            UserLoginInfo userLoginInfo = (UserLoginInfo) request.getSession().getAttribute("userLoginInfo");
            if (userLoginInfo != null) {
                UsernameAuthentication token = new UsernameAuthentication();
                token.setCurrentUser(userLoginInfo);
                token.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request, response);
    }
}

