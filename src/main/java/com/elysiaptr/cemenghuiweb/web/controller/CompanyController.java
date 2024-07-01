package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.CompanyDto;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

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
        company.setLogo(companyDto.getLogo());
        company.setContact(companyDto.getContact());
        Instant i = Instant.now();
//        Instant a = Instant.from(LocalDateTime.now());
//        System.out.println(a);
        System.out.println(i);
//        System.out.println(a);
        company.setTime(i);
        company.setMobile(companyDto.getMobile());
        company.setRemark(companyDto.getRemark());
        companyService.saveCompany(company);
        return R.OK().data("提示", "新增公司成功");
    }
    @PostMapping("/delete")
    public R deleteCompany(@RequestBody CompanyDto companyDto) {
        Company company = new Company();
        company.setId(companyDto.getId());
        companyService.deleteCompany(company.getId());
        return R.OK().data("提示", "删除公司成功");
    }
    @PostMapping("/update")
    public R updateCompany(@RequestBody CompanyDto companyDto) {
        Company company = new Company();
        company.setId(companyDto.getId());
        company.setName(companyDto.getName());
        company.setLogo(companyDto.getLogo());
        company.setContact(companyDto.getContact());
        company.setMobile(companyDto.getMobile());
        Instant i = Instant.now();
        Instant a = Instant.from(LocalDateTime.now());
        company.setTime(i);
        company.setRemark(companyDto.getRemark());
        companyService.updateCompany(company.getId(), company);
        return R.OK().data("提示", "修改公司成功");
    }
    @GetMapping("/search_by_list")
    public R searchByListCompany(@RequestParam(required = false) String contact,
                                 @RequestParam(required = false) Long companyId,
                                 @RequestParam(required = false) Long mobile,
                                 @RequestParam(required = false) String companyName) {
        List<Company> companyList = companyService.getAllCompanies();
        if (companyName != null) {
            companyList=companyService.searchByCompanyName(companyName);

        }
        if (mobile != null) {
            companyList=companyService.searchByCompanyMobile(mobile);

        }
        if (contact != null) {
            companyList=companyService.searchByCompanyContact(contact);

        }
        if (companyId != null) {
            companyList=companyService.searchByCompanyId(companyId);

        }

        return R.OK().data("companyList", companyList);
    }
    @GetMapping("/search_all")
    public R searchAllCompany() {
        List<Company> companyList = companyService.getAllCompanies();
        return R.OK().data("companyListAll", companyList);
    }

}
