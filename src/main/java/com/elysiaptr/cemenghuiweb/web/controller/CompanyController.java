package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.CompanyDto;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open_api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    private UserService userService;
    @PostMapping("/add")
    public R addCompany(@RequestBody CompanyDto companyDto) {
        if(companyDto==null){
            return R.error().message("租户信息不能为空");
        }
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setContact(companyDto.getContact());
        company.setMobile(companyDto.getMobile());
        company.setUsers(companyDto.);
        company.setRemark(companyDto.getRemark());
        User user=userService.getUserByUserName(String userName)
        return R.OK().data("提示", "新增公司成功");
    }
}
