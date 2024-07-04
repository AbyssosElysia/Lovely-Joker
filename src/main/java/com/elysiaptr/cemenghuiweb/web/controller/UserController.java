package com.elysiaptr.cemenghuiweb.web.controller;
import com.alibaba.excel.EasyExcel;
import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.*;
import com.elysiaptr.cemenghuiweb.web.po.*;
import com.elysiaptr.cemenghuiweb.web.repo.UserRepository;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.DepartmentService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


import java.time.Instant;

import com.alibaba.excel.EasyExcel;


import java.io.ByteArrayOutputStream;
@RestController
@RequestMapping("/open_api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DepartmentService departmentService;
//新增
    @PostMapping("/add")

    public R addUser(@RequestBody UserDto userDto){
        // 非空
        if (userDto == null) {
            return R.error().message("用户信息不能为空");
        }
        User user = new User();
//        user.setUsername(userDto.getUsername());
//        user.setPassword(userDto.getPassword());
//        user.setName(userDto.getName());
//        user.setMobile(userDto.getMobile());
//        user.setGender(userDto.getGender());
//        user.setEmail(userDto.getEmail());
//        user.setTime(new Date().toInstant());
//        user.setRole(userDto.getRole());
        BeanUtils.copyProperties(userDto, user);
        user.setTime(new Date().toInstant());

       /* Company company = companyService.getCompanyById(userDto.getCompany_id());
        if (company == null) {
            return R.error().message("无效的公司ID");
        }
        user.setCompany(company);*/
        userService.saveUser(user);
        return R.OK().data("提示", "新增用户成功");
    }


//删除
    @PostMapping("/delete")
    public R deleteItems(@RequestBody ListDto listDto) {
        List<ItemDto> items = listDto.getItems();
        if (items != null ) {
            for (ItemDto item : items) {
                User existingItem = userService.getUserById(item.getId());
                if (existingItem != null && existingItem.getName().equals(item.getName())) {
                    // 调用服务层删除对应的 item
                    userService.deleteUser(item.getId());
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
    public R updateUser(@RequestBody UserDto userDto){
        userService.updateUser(userDto.getId(), userDto);
        return R.OK().data("提示", "修改信息成功");
    }
    //查找
    @GetMapping("/search_by_list")
    public R searchByListUser(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Long mobile,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Instant time,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        // 先进行筛选
        List<User> userList = userService.getAllUsers();
        Object List;
        if (username != null) {
            userList = userService.searchByUsername(username, userList);
        }
        if (mobile != null) {
            userList = userService.searchByMobile(mobile, userList);
        }
        if (status != null) {
            userList = userService.searchByStatus(status, userList);
        }
        if (time != null) {
            userList = userService.searchByTime(time, userList);
        }

        // 再进行分页
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), userList.size());
        List<User> pageList = userList.subList(start, end);

        List<UserDto> userDtos = pageList.stream()
                .map(user -> {
                    UserDto dto = new UserDto();
                    dto.setId(user.getId());
                    dto.setName(user.getName());
                    dto.setMobile(user.getMobile());
                    dto.setUsername(user.getUsername());
                    dto.setStatus(user.getStatus());
                    dto.setTime(user.getTime().toString());
                    dto.setDepartmentName(user.getDept().getName());
                    return dto;
                })
                .collect(Collectors.toList());

        Page<UserDto> userDtoPage = new PageImpl<>(userDtos, pageable, userList.size());

        return R.OK().data("userList", userDtoPage);
    }
//查all
@GetMapping("/search_page")
public R searchPage(@RequestParam(required = false, defaultValue = "0") int page,
                    @RequestParam(required = false, defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<User> userPage = userService.getUsersByPage(pageable.getPageNumber(),pageable.getPageSize());

    List<UserDto> userDtos = userPage.getContent().stream()
            .map(user -> {
                UserDto dto = new UserDto();
                dto.setId(user.getId());
                dto.setUsername(user.getUsername());
                dto.setMobile(user.getMobile());
                dto.setStatus(user.getStatus());
                dto.setName(user.getName());
                dto.setTime(user.getTime().toString());
                dto.setDepartmentName(user.getDept().getName());
                return dto;
            })
            .collect(Collectors.toList());

    Page<UserDto> userDtoPage = new PageImpl<>(userDtos, pageable, userPage.getTotalElements());

    return R.OK().data("userList", userDtoPage);
    }

    @GetMapping("/search_tree")
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

    @PostMapping("/export")
    public ResponseEntity<byte[]> exportUsers(@RequestBody List<Long> userIds) {
        List<User> userList = userService.getUsersByIds(userIds);

        // 将 User 列表转换为 UserExportDto 列表
        List<UserDto> userDtos = userList.stream()
                .map(user -> {
                    UserDto dto = new UserDto();
                    dto.setId(user.getId());
                    dto.setName(user.getName());
                    dto.setUsername(user.getUsername());
                    dto.setDepartmentName(user.getDept().getName()); // 这里根据实际情况设置部门名称
                    dto.setMobile(user.getMobile());
                    dto.setStatus(user.getStatus());
                    dto.setTime(user.getTime().toString());
                    return dto;
                })
                .collect(Collectors.toList());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            // EasyExcel 写入数据到 Excel 文件
            EasyExcel.write(outputStream, UserDto.class)
                    .sheet("Users")
                    .doWrite(userDtos);

            byte[] excelBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "users.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelBytes);

        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/search_by_id")
    public R searchUserById(@RequestParam(required = false) Long id) {

        User user = userService.getUserById(id);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setMobile(user.getMobile());
        userDto.setDepartmentName(user.getDept().getName());
        return R.OK().data("user", userDto);
    }
    //搜索部门
    /*@GetMapping("/search_department")
    public R searchDepartment(@RequestParam String departmentName) {
        //String departmentName = searchDto.getDepartmentName();
        List<Company> companies = new ArrayList<>();
        List<DepartmentDto> departments = new ArrayList<>();

        // 获取所有公司
        List<Company> allCompanies = companyService.getAllCompanies();

        for (Company company : allCompanies) {
            List<Department> companyDepartments = departmentService.searchDepartmentByCompany(company);

            for (Department department : companyDepartments) {
                if (department.getName().contains(departmentName)) {
                    if (!companies.contains(company)) {
                        companies.add(company);
                    }

                    // 构建部门树
                    if (department.getFatherDept() == null) {
                        Stack<DepartmentDto> departmentDtoStack = new Stack<>();
                        DepartmentDto departmentDto = (DepartmentDto) departmentService.DFS(department, departmentDtoStack).get("department");
                        departments.add(departmentDto);
                    }
                }
            }
        }

        return R.OK().data("companyList", companies).data("departmentList", departments);
    }

*/
    @GetMapping("/search_department")
    public R searchDepartment(@RequestParam String departmentName) {
        List<CompanyDto> companyDtos = new ArrayList<>();

        // 获取所有公司
        List<Company> allCompanies = companyService.getAllCompanies();

        for (Company company : allCompanies) {
            List<Department> companyDepartments = departmentService.searchDepartmentByCompany(company);
            for (Department department : companyDepartments) {
                if (department.getName().contains(departmentName)) {
                    CompanyDto companyDto = new CompanyDto();
                    companyDto.setId(company.getId());
                    companyDto.setName(company.getName());

                    DepartmentDto departmentDto = new DepartmentDto();
                    departmentDto.setId(department.getId());
                    departmentDto.setName(department.getName());
                    departmentDto.setStatus(department.getStatus());

                    companyDto.getDepartments().add(departmentDto);

                    companyDtos.add(companyDto);
                    break; // Assuming one department can belong to only one company
                }
            }
        }

        return R.OK().data("companyList", companyDtos);
    }

}
