package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.DepartmentDto;
import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.Department;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/open_api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping("/add")
    public R addDepartment(@RequestBody DepartmentDto departmentDto) {
        if (departmentDto == null) {
            return R.error().message("部门信息不能为空");
        }
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        long fatherDeptId = Long.parseLong(departmentDto.getFatherDept_id());
        department.setFatherDept(departmentService.getDepartmentById(fatherDeptId));
        department.setEmail(departmentDto.getEmail());
        department.setMobile(departmentDto.getMobile());
        department.setStatus(departmentDto.getStatus());
//负责人不知道怎么写
        departmentService.saveDepartment(department);
        return R.OK().data("提示", "新增部门成功");
    }
    //删除
    @PostMapping("/delete")
    public R deleteDepartment(@RequestBody DepartmentDto departmentDto){
        departmentService.deleteDepartment(departmentDto.getId());
        return R.OK().data("提示", "删除用户成功");
    }
    //修改
    @PostMapping("/update")
    public R updateDepartment(@RequestBody DepartmentDto departmentDto){
        Department department=new Department();
        department.setId(departmentDto.getId());
        department.setFatherDept(department.getFatherDept());
        department.setName(department.getName());
        department.setEmail(department.getEmail());
        department.setMobile(department.getMobile());
        department.setStatus(department.getStatus());
        //负责人
        departmentService.updateDepartment(department.getId(),department);
        return R.OK().data("提示", "修改信息成功");
    }
    //查找
    @GetMapping("/search_by_list")
    public R searchByListUser( @RequestParam(required = false) String name,
                               @RequestParam(required = false) Byte status) {
        List<Department> departmentList=departmentService.getAllDepartments();
        if (status != null) {
            departmentList=departmentService.searchDepartmentByStatus(status);
        }
        if (name != null) {
            departmentList=departmentService.searchDepartmentByName(name);

        }

        return R.OK().data("departmentList", departmentList);
    }
    //查all
    @GetMapping("/search_all")
    public R searchAllDepartment() {
        List<Department> departmentList = departmentService.getAllDepartments();
        return R.OK().data("departmentList", departmentList);
    }

}
