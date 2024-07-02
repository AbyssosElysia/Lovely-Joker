/*
package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.RegisterDto;
import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/open_api/register/")
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @PostMapping("/company_form1")
    public R register(@RequestBody RegisterDto registerDto) {
        if(registerDto==null){
            return R.error().message("租户信息不能为空");
        }
        Company company = new Company();
        //租户名
        company.setName(registerDto.getCompanyName());
        //联系人姓名
        company.setContact(registerDto.getContact());
        //联系人账号
        //userService.getUserByName(registerDto.getUserName()).setUsername(registerDto.getUserName());
        Instant instant = Instant.now();
        company.setTime(instant);//时间为啥必须非空
        //Long mobileNumber=Long.parseLong(registerDto.getMobile());
        //company.setMobile(mobileNumber);

        //
        //密码
        //userService.getUserByName(registerDto.getUserName()).setPassword(registerDto.getPassword());
        User user = new User();
        user.setUsername(registerDto.getContact());
        user.setCompany(company);
        user.setPassword(registerDto.getPassword());
        user.setTime(instant);
        user.setName(registerDto.getContact());
        user.setEmail(registerDto.getEmail());
        user.setMobile(registerDto.getMobile());
        user.setStatus(registerDto.getStatus());
        user.setGender(registerDto.getGender());
        user.setRole(registerDto.getRole());
        userService.saveUser(user);
        companyService.saveCompany(company);
        return R.OK().data("提示", "新增租户成功");

    }
    @PostMapping("/user_form2")
    public R register(@RequestBody UserDto userDto) {
        if(userDto==null){
            return R.error().message("管理员信息不能为空");
        }
        User user = new User();

        Instant instant = Instant.now();
        user.setTime(instant);//时间为啥必须非空
        //验证码
        userService.saveUser(user);
        return R.OK().data("提示", "完善管理员信息成功");
    }
}
*/
