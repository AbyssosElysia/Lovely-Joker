package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.repo.UserRepository;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/open_api")
public class UserController {
    @Autowired
    private UserService userService;
    private CompanyService companyService;

//新增
    @PostMapping("/user/add")
    public R addUser(@RequestBody UserDto userDto){
        // 非空
        if (userDto == null) {
            return R.error().message("用户信息不能为空");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setMobile(userDto.getMobile());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        Company company = companyService.getCompanyById((long) userDto.getCompany_id());
        if (company == null) {
            return R.error().message("无效的公司ID");
        }
        user.setCompany(company);
        userService.saveUser(user);
        return R.OK().data("提示", "新增用户成功");
    }


//删除
    @PostMapping("/user/delete")
    public R deleteUser(@RequestBody UserDto userDto){
        User user=new User();
        user.setUsername(userDto.getUsername());
        userService.deleteUser(user.getId());
        return R.OK().data("提示", "删除用户成功");
    }

//修改
    @PostMapping("/user/update")
    public R updateUser(@RequestBody UserDto userDto){
        User user=new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(user.getPassword());
        user.setGender(userDto.getGender());
        user.setMobile(userDto.getMobile());
        user.setEmail(userDto.getEmail());
        userService.updateUser(user.getId(), user);
        return R.OK().data("提示", "修改信息成功");
    }
//查找
@GetMapping("/user/search_by_list")
public R searchByListUser(@RequestParam(required = false) String username,
                          @RequestParam(required = false) Long mobile,
                          @RequestParam(required = false) Integer status,
                          @RequestParam(required = false) LocalDateTime time) {
    List<User> userList = userService.getAllUsers();

    if (username != null) {
        userList=userService.searchByUsername(username);
//        userList = userList.stream()
//                .filter(user -> user.getUsername().contains(username))
//                .collect(Collectors.toList());
    }
    if (mobile != null) {
        userList=userService.searchByMobile(mobile);
//        userList = userList.stream()
//                .filter(user -> user.getMobile().equals(mobile))
//                .collect(Collectors.toList());
    }
    if (status != null) {
        userList=userService.searchByStatus(status);
//        userList = userList.stream()
//                .filter(user -> user.getStatus().equals(status))
//                .collect(Collectors.toList());
    }
    if (time != null) {
        userList=userService.searchByTime(time);
//        userList = userList.stream()
//                .filter(user -> user.getTime().equals(time))
//                .collect(Collectors.toList());
    }

    return R.OK().data("userList", userList);
}
//查all
@GetMapping("/user/search_all")
public R searchAllUser() {
    List<User> userList = userService.getAllUsers();
    return R.OK().data("userListAll", userList);

    }
}

