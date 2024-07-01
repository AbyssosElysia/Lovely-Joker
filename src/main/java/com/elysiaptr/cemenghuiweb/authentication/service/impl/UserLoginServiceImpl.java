package com.elysiaptr.cemenghuiweb.authentication.service.impl;

import com.alibaba.fastjson2.JSON;
import com.elysiaptr.cemenghuiweb.authentication.dto.LoginUser;
import com.elysiaptr.cemenghuiweb.authentication.dto.LoginUserDto;
import com.elysiaptr.cemenghuiweb.authentication.service.UserLoginService;
import com.elysiaptr.cemenghuiweb.common.utils.JwtUtils;
import com.elysiaptr.cemenghuiweb.web.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginUserDto user) {
        // 封装Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());
        // 校验
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        // 如果authentication为空
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("Login failed");
        }
        // 放入用户信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成jwt并转化为字符串
        String loginUserString = JSON.toJSONString(loginUser);
        // 生成jwt令牌
        return JwtUtils.generateJWT(loginUserString, null);
    }
}
