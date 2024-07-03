package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.CompanyDto;
import com.elysiaptr.cemenghuiweb.web.dto.ItemDto;
import com.elysiaptr.cemenghuiweb.web.dto.ListDto;
import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        BeanUtils.copyProperties(companyDto, company);
        company.setTime(new Date().toInstant());
//        Instant i = Instant.now();
//        Instant a = Instant.from(LocalDateTime.now());
//        System.out.println(a);
     //   System.out.println(i);
//        System.out.println(a);
      //  company.setTime(i);
        companyService.saveCompany(company);
        return R.OK().data("提示", "新增公司成功");
    }
    @PostMapping("/delete")
    public R deleteItems(@RequestBody ListDto listDto) {
        List<ItemDto> items = listDto.getItems();
        if (items != null && !items.isEmpty()) {
            for (ItemDto item : items) {
                Company existingItem = companyService.getCompanyById(item.getId());
                if (existingItem != null && existingItem.getName().equals(item.getName())) {
                    // 调用服务层删除对应的 item
                    companyService.deleteCompany(item.getId());
                } else {
                    return R.error().data("提示", "ID 为 " + item.getId() + " 的项目不存在或名称不匹配");
                }
            }
            return R.OK().data("提示", "批量删除成功");
        } else {
            return R.error().data("提示", "请选择需要删除的项目");
        }
    }
    @PostMapping("/update")
    public R updateCompany(@RequestBody CompanyDto companyDto) {
        companyService.updateCompany(companyDto.getId(), companyDto);
        return R.OK().data("提示", "修改公司成功");
    }
    @GetMapping("/search_by_list")
    public R searchByListCompany(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) Long mobile,
            @RequestParam(required = false) String contact,
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        // 先进行筛选
        List<Company> companyList = companyService.getAllCompanies();

        if (companyName != null) {
            companyList = companyService.searchByCompanyName(companyName,companyList);

        }
        if (mobile != null) {
            companyList = companyService.searchByCompanyMobile(mobile,companyList);
        }
        if (contact != null) {
            companyList = companyService.searchByCompanyContact(contact,companyList);
        }
        if (companyId != null) {
            companyList = companyService.searchByCompanyId(companyId,companyList);
        }

        // 再进行分页
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), companyList.size());
        List<Company> pageList = companyList.subList(start, end);

        List<CompanyDto> companyDtos = pageList.stream()
                .map(company -> {
                    CompanyDto dto = new CompanyDto();
                    dto.setId(company.getId());
                    dto.setName(company.getName());
                    dto.setMobile(company.getMobile());
                    dto.setContact(company.getContact());
                    return dto;
                })
                .collect(Collectors.toList());

        Page<CompanyDto> companyDtoPage = new PageImpl<>(companyDtos, pageable, companyList.size());

        return R.OK().data("companyList", companyDtoPage);
    }
    @GetMapping("/search_page")
    public R searchPage(@RequestParam(required = false, defaultValue = "0") int page,
                        @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Company> companyPage = companyService.getCompaniesByPage(pageable.getPageNumber(),pageable.getPageSize());

        List<CompanyDto> companyDtos = companyPage.getContent().stream()
                .map(company -> {
                    CompanyDto dto = new CompanyDto();
                    dto.setId(company.getId());
                    dto.setContact(company.getContact());
                    dto.setName(company.getName());
                    dto.setMobile(company.getMobile());
                    dto.setAdminName(company.getContact());
                    return dto;
                })
                .collect(Collectors.toList());

        Page<CompanyDto> companyDtoPage = new PageImpl<>(companyDtos, pageable, companyPage.getTotalElements());

        return R.OK().data("companyList", companyDtoPage);
    }

}
