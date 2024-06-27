package com.elysiaptr.cemenghuiweb.authentication.handler.login.username;

import com.alibaba.fastjson2.JSON;
import com.elysiaptr.cemenghuiweb.authentication.dto.LoginUser;
import com.elysiaptr.cemenghuiweb.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        System.out.println(url);

        if (url.equals("/api/login/username")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            throw new RuntimeException("Token is empty");
        }

        String loginUserStr = null;
        LoginUser loginUser = null;

        try {
            Claims claims = JwtUtils.parseJWT(token);
            loginUserStr = claims.getSubject();
            loginUser = JSON.parseObject(loginUserStr, LoginUser.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Token is wrong");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
