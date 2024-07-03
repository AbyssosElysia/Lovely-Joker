package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.*;
import com.elysiaptr.cemenghuiweb.web.po.*;
import com.elysiaptr.cemenghuiweb.web.repo.DepartmentRepository;
import com.elysiaptr.cemenghuiweb.web.service.DepartmentService;
import com.elysiaptr.cemenghuiweb.web.service.impl.CompanyServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/open_api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CompanyServiceImpl companyService;
    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/add")
    public R addDepartment(@RequestBody DepartmentDto departmentDto) {
        if (departmentDto == null) {
            return R.error().message("部门信息不能为空");
        }
        Department department = new Department();
        department.setCompany(companyService.getCompanyById(departmentDto.getCompanyId()));
        department.setName(departmentDto.getName());
        department.setId(departmentDto.getId());
        department.setStatus(departmentDto.getStatus());
        department.setMobile(departmentDto.getMobile());
        department.setEmail(departmentDto.getEmail());
        department.setManager(departmentDto.getLeader());
        department.setFatherDept(departmentService.getDepartmentById(departmentDto.getFatherDeptId()));
        Instant instant = Instant.now();
        department.setTime(instant);
        //逐个写
        departmentService.saveDepartment(department);
        return R.OK().data("提示", "新增部门成功");
    }

    //删除
    @PostMapping("/delete")
    public R deleteItems(@RequestBody ListDto listDto) {
        List<ItemDto> items = listDto.getItems();
        if (items != null && !items.isEmpty()) {
            for (ItemDto item : items) {
                Department existingItem = departmentService.getDepartmentById(item.getId());
                if (existingItem != null && existingItem.getName().equals(item.getName())) {
                    // 调用服务层删除对应的 item
                    departmentService.deleteDepartment(item.getId());
                } else {
                    return R.error().data("提示", "ID 为 " + item.getId() + " 的项目不存在或名称不匹配");
                }
            }
            return R.OK().data("提示", "批量删除成功");
        } else {
            return R.error().data("提示", "请选择需要删除的项目");
        }
    }

    //修改
    @PostMapping("/update")
    public R updateDepartment(@RequestBody DepartmentDto departmentDto) {
       /* Department department=new Department();
        department.setId(departmentDto.getId());
        department.setFatherDept(department.getFatherDept());
        department.setName(department.getName());
        department.setEmail(department.getEmail());
        department.setMobile(department.getMobile());
        department.setStatus(department.getStatus());*/
        //负责人
        departmentService.updateDepartment(departmentDto.getId(), departmentDto);
        return R.OK().data("提示", "修改信息成功");
    }

    @GetMapping("/search_by_list")
    public R searchByListUser(@RequestParam(required = false) String name,
                              @RequestParam(required = false) Byte status,
                              @RequestParam(required = false, defaultValue = "0") int page,
                              @RequestParam(required = false, defaultValue = "10") int size) {
        // 先进行筛选
        List<Department> departmentList = departmentService.getAllDepartments();


        if (name != null) {
            departmentList=departmentService.searchDepartmentByName(departmentList,name);
        }
        if (status != null) {
            departmentList=departmentService.searchDepartmentByStatus(departmentList,status);
        }

        // 再进行分页
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), departmentList.size());
        List<Department> pageList = departmentList.subList(start, end);

        List<DepartmentDto> departmentDtos = pageList.stream()
                .map(department -> {
                    DepartmentDto dto = new DepartmentDto();
                    dto.setStatus(department.getStatus());
                    dto.setName(department.getName());
                    return dto;
                })
                .collect(Collectors.toList());

        Page<DepartmentDto> departmentDtoPage = new PageImpl<>(departmentDtos, pageable, departmentList.size());

        return R.OK().data("departmentList", departmentDtoPage);
    }

    //查all
    @GetMapping("/search_all")
//    public R searchAllDepartment() {
//        return R.OK();
//    }
    public R searchAllDepartment(@RequestParam(required = true) Long companyId) {
        //获取公司的部门{
        Company company = companyService.getCompanyById(companyId);
        List<DepartmentDto> departments=new ArrayList<DepartmentDto>();
       /* List<DepartmentDto> departmentDtos = departmentService.searchDepartmentByCompany(company).stream()
                .map(department -> {
                    DepartmentDto dto = new DepartmentDto();
                    dto.setId(department.getId());
                    dto.setName(department.getName());
                    dto.setStatus(department.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());*/
        // System.out.println( departmentService.searchDepartmentByCompany(company));

        for (Department department : departmentService.searchDepartmentByCompany(company)) {
            if (department.getFatherDept() == null) {
                Stack<DepartmentDto> departmentDtoStack = new Stack<>();
                DepartmentDto departmentDto = (DepartmentDto) departmentService.DFS(department, departmentDtoStack).get("department");
                departments.add(departmentDto);
            }

        }
        return R.OK().data("departmentList", departments);
    }


}




