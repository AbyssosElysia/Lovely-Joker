package com.elysiaptr.cemenghuiweb.authentication.controller;

import com.elysiaptr.cemenghuiweb.authentication.dto.UserDto;
import com.elysiaptr.cemenghuiweb.authentication.service.UserLoginService;
import com.elysiaptr.cemenghuiweb.common.consts.RedisKeyPrefixes;
import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.common.utils.StringRedisUtils;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class UserLoginController {

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    StringRedisUtils stringRedisUtils;

    @Autowired
    UserService userService;

    @PostMapping("/username")
    public R login(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        System.out.println(userDto.getCaptcha());
        String jwt = userLoginService.login(user);
        String key = RedisKeyPrefixes.PREFIX_CAPTCHA + userDto.getUuid();
        if (StringUtils.hasLength(jwt) && userDto.getCaptcha().equals(stringRedisUtils.get(key))) {
            User thisUser = userService.getUserByUsername(user.getUsername());
            int authentication = thisUser.getRole();
            if (authentication == 0) {
                return R.OK().message("Login success").data("token", jwt).data("user", thisUser).data("authentication", authentication).data("company", thisUser.getCompany());
            }
            return R.OK().message("Login success").data("token", jwt).data("user", thisUser).data("authentication", authentication);
        }
        stringRedisUtils.delete(key);
        return R.notFound().message("Login failed");
    }
}
