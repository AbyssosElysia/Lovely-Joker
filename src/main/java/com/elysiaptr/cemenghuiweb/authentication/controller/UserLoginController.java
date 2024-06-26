package com.elysiaptr.cemenghuiweb.authentication.controller;

import com.elysiaptr.cemenghuiweb.authentication.service.UserLoginService;
import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/login")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/username")
    public R login(@RequestBody User user) {
        String jwt = userLoginService.login(user);
        if (StringUtils.hasLength(jwt)) {
            return R.OK().message("Login success").data("token", jwt);
        }
        return R.notFound().message("Login failed");
    }
}
