package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.consts.RedisKeyPrefixes;
import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.common.utils.EmailUtils;
import com.elysiaptr.cemenghuiweb.common.utils.RandomStringUtils;
import com.elysiaptr.cemenghuiweb.common.utils.StringRedisUtils;
import com.elysiaptr.cemenghuiweb.web.dto.RegisterDto;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/open_api")
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    StringRedisUtils stringRedisUtils;
    @Autowired
    RandomStringUtils randomStringUtils;
    @Autowired
    EmailUtils emailUtils;

    // 点击获取验证码
    @PostMapping("/verification_code")
    public R verifyCode(@RequestParam String to, @RequestParam String username) {
        // 生成一个6位随机验证码
        String verificationCode = randomStringUtils.generateVerificationCode(6);
        // 使用前缀和用户名构建Redis键
        String key = RedisKeyPrefixes.RESET_CAPTCHA + username;
        // 将验证码存储到Redis中
        stringRedisUtils.set(key, verificationCode);
        // 设置该键的过期时间为3分钟
        stringRedisUtils.expire(key, 3, TimeUnit.MINUTES);
        // 发送包含验证码的电子邮件
        emailUtils.sendEmail(to, "验证码", "邮箱验证码为" + verificationCode + ", 请于3分钟内进行验证,逾期验证码无效!");

        return R.OK().data("message", "验证码已发送");
    }

    // 注册新用户
    @PostMapping("/register")
    public R register(@RequestBody RegisterDto registerDto, @RequestParam String verificationCode) {
        if (registerDto == null) {
            return R.error().message("租户信息不能为空");
        }

        // 验证验证码
        String key = RedisKeyPrefixes.RESET_CAPTCHA + registerDto.getUserName();
        String storedCode = stringRedisUtils.get(key);
        if (storedCode == null || !storedCode.equals(verificationCode)) {
            return R.error().data("error", "验证码错误或已过期");
        }

        // 删除验证码以防重复使用
        stringRedisUtils.delete(key);

        // 创建并设置Company对象
        Company company = new Company();
        company.setName(registerDto.getCompanyName());
        company.setContact(registerDto.getContact());
        Instant instant = Instant.now();
        company.setTime(instant);
        company.setMobile(registerDto.getMobile());
        companyService.saveCompany(company);

        // 创建并设置User对象
        User user = new User();
        user.setUsername(registerDto.getUserName());
        user.setCompany(company);
        user.setPassword(registerDto.getPassword()); // 这里建议对密码进行加密处理
        user.setTime(instant);
        user.setName(registerDto.getContact());
        user.setEmail(registerDto.getEmail());
        user.setMobile(registerDto.getMobile());
        user.setStatus(registerDto.getStatus());
        user.setGender(registerDto.getGender());
        user.setRole(registerDto.getRole());
        userService.saveUser(user);

        return R.OK().data("提示", "新增租户成功");
    }
}
