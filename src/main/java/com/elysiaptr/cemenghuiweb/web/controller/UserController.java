package com.elysiaptr.cemenghuiweb.web.controller;
import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.ItemDto;
import com.elysiaptr.cemenghuiweb.web.dto.ListDto;
import com.elysiaptr.cemenghuiweb.web.dto.NewsDto;
import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.News;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.repo.UserRepository;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/open_api/user")
public class UserController {
    @Autowired
    private UserService userService;
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
        if (username != null) {
            userList = userService.searchByUsername(username);
        }
        if (mobile != null) {
            userList = userService.searchByMobile(mobile);
        }
        if (status != null) {
            userList = userService.searchByStatus(status);
        }
        if (time != null) {
            userList = userService.searchByTime(time);
        }

        // 再进行分页
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), userList.size());
        List<User> pageList = userList.subList(start, end);

        Page<User> userPage = new PageImpl<>(pageList, pageable, userList.size());

        return R.OK().data("userList", userPage);
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
                dto.setTime(user.getTime());
                dto.setDepartmentName(user.getDept().getName());
                return dto;
            })
            .collect(Collectors.toList());

    Page<UserDto> userDtoPage = new PageImpl<>(userDtos, pageable, userPage.getTotalElements());

    return R.OK().data("userList", userDtoPage);
    }
}
