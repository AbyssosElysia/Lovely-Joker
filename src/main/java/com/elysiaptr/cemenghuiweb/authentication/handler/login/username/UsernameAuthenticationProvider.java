package com.elysiaptr.cemenghuiweb.authentication.handler.login.username;


import com.elysiaptr.cemenghuiweb.authentication.handler.login.UserLoginInfo;
import com.elysiaptr.cemenghuiweb.authentication.service.AuUserService;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.utils.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 账号密码登录认证
 */
@Component
public class UsernameAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsernameAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 用户提交的用户名 + 密码：
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        // 查数据库，匹配用户信息
        User user = userService.getUserFromDB(username);
        if (user == null
                || !passwordEncoder.matches(password, user.getPassword())) {
            // 密码错误，直接抛异常。
            // 根据SpringSecurity框架的代码逻辑，认证失败时，应该抛这个异常：org.springframework.security.core.AuthenticationException
            // BadCredentialsException就是这个异常的子类
            // 抛出异常后后，AuthenticationFailureHandler的实现类会处理这个异常。
            throw new BadCredentialsException("${invalid.username.or.pwd:用户名或密码不正确}");
        }

        UsernameAuthentication token = new UsernameAuthentication();
        token.setCurrentUser(JSON.convert(user, UserLoginInfo.class));
        token.setAuthenticated(true); // 认证通过，这里一定要设成true

        // 将用户信息存储在会话中
        SecurityContextHolder.getContext().setAuthentication(token);

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernameAuthentication.class);
    }
}
