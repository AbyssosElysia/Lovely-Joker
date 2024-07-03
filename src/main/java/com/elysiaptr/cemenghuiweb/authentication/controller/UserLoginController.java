package com.elysiaptr.cemenghuiweb.authentication.controller;

import com.elysiaptr.cemenghuiweb.authentication.dto.CompanyReturnDto;
import com.elysiaptr.cemenghuiweb.authentication.dto.LoginUserDto;
import com.elysiaptr.cemenghuiweb.authentication.dto.UserDto;
import com.elysiaptr.cemenghuiweb.authentication.dto.UserReturnDto;
import com.elysiaptr.cemenghuiweb.authentication.service.UserLoginService;
import com.elysiaptr.cemenghuiweb.common.consts.RedisKeyPrefixes;
import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.common.utils.StringRedisUtils;
import com.elysiaptr.cemenghuiweb.web.po.SuperAdmin;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.repo.SuperAdminRepository;
import com.elysiaptr.cemenghuiweb.web.service.SuperAdminService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    SuperAdminRepository superAdminRepository;

    @PostMapping("/username")
    public R login(@RequestBody UserDto userDto) {
        String key = RedisKeyPrefixes.PREFIX_CAPTCHA + userDto.getUuid();
        SuperAdmin superAdmin = superAdminRepository.findByUsername(userDto.getUsername());
        if (superAdmin != null && userDto.getCaptcha().equals(stringRedisUtils.get(key))) {
            if (superAdmin.getPassword().equals(userDto.getPassword())) {
                return R.OK().message("Login success").data("user", superAdmin.getId()).data("authentication", 2);
            }
            return R.notFound().message("Login failed");
        }
        LoginUserDto user = new LoginUserDto();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        System.out.println(userDto.getCaptcha());
        String jwt = userLoginService.login(user);
        if (StringUtils.hasLength(jwt) && userDto.getCaptcha().equals(stringRedisUtils.get(key))) {
            User thisUser = userService.getUserByUsername(user.getUsername());
            UserReturnDto userReturnDto = new UserReturnDto();
            userReturnDto.setId(thisUser.getId());
            userReturnDto.setName(thisUser.getName());
            userReturnDto.setUsername(thisUser.getUsername());
            userReturnDto.setMobile(thisUser.getMobile());
            userReturnDto.setGender(thisUser.getGender());
            userReturnDto.setEmail(thisUser.getEmail());
            userReturnDto.setStatus(thisUser.getStatus());
            userReturnDto.setTime(thisUser.getTime());
            userReturnDto.setRole(thisUser.getRole());
            userReturnDto.setRemark(thisUser.getRemark());
            userReturnDto.setDepartmentId(thisUser.getDept().getId());
            userReturnDto.setDepartmentName(thisUser.getDept().getName());
            userReturnDto.setCompanyId(thisUser.getCompany().getId());
            userReturnDto.setCompanyName(thisUser.getCompany().getName());
            userReturnDto.setPostId(thisUser.getPost().getId());
            userReturnDto.setPostName(thisUser.getPost().getName());
            CompanyReturnDto companyReturnDto = new CompanyReturnDto();
            companyReturnDto.setId(thisUser.getCompany().getId());
            companyReturnDto.setName(thisUser.getCompany().getName());
            companyReturnDto.setMobile(thisUser.getCompany().getMobile());
            companyReturnDto.setContact(thisUser.getCompany().getContact());
            companyReturnDto.setLogo(thisUser.getCompany().getLogo());
            companyReturnDto.setTime(thisUser.getCompany().getTime());
            companyReturnDto.setRemark(thisUser.getCompany().getRemark());
            int authentication = thisUser.getRole();
            if (authentication == 0) {
                return R.OK().message("Login success").data("token", jwt).data("user", userReturnDto.getId()).data("authentication", 0);
            }
            return R.OK().message("Login success").data("token", jwt).data("user", thisUser.getId()).data("authentication", 1);
        }
        stringRedisUtils.delete(key);
        return R.notFound().message("Login failed");
    }
}
