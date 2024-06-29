package com.elysiaptr.cemenghuiweb.authentication.controller;

import com.elysiaptr.cemenghuiweb.common.consts.RedisKeyPrefixes;
import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.common.utils.EmailUtils;
import com.elysiaptr.cemenghuiweb.common.utils.RandomStringUtils;
import com.elysiaptr.cemenghuiweb.common.utils.StringRedisUtils;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/open_api")
public class EmailController {

    @Autowired
    EmailUtils emailUtils;

    @Autowired
    RandomStringUtils randomStringUtils;

    @Autowired
    UserService userService;

    @Autowired
    StringRedisUtils stringRedisUtils;

    @GetMapping("/email/code")
    public R emailCode(@RequestParam String username, @RequestParam String to) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return R.notFound().data("error", "user not found");
        }
        if (!user.getEmail().equals(to)) {
            return R.error().data("error", "email not match");
        }
        String verificationCode = randomStringUtils.generateVerificationCode(6);
        String key = RedisKeyPrefixes.RESET_CAPTCHA + username;
        stringRedisUtils.set(key, verificationCode);
        stringRedisUtils.expire(key, 3, TimeUnit.MINUTES);
        emailUtils.sendEmail(to, "找回密码", "邮箱验证码为" + verificationCode + ", 请于3min内进行验证,逾期验证码无效!");
        return R.OK().data("data", "Sent Successfully");
    }

    @GetMapping("/email/reset_code")
    public R resetCode(@RequestParam String username, @RequestParam String verificationCode, @RequestParam String password) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return R.notFound().data("error", "user not found");
        }
        String key = RedisKeyPrefixes.RESET_CAPTCHA + username;
        if (!stringRedisUtils.get(key).equals(verificationCode)) {
            stringRedisUtils.delete(key);
            return R.error().data("error", "code not match");
        }
        user.setPassword(password);
        return R.OK().data("data", "reset code success");
    }
}
