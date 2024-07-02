package com.elysiaptr.cemenghuiweb.web.dto;

import com.elysiaptr.cemenghuiweb.web.po.Department;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDto {
    private Long id;
    private String name;
    private Long mobile;
    private String email;
    private Byte status;
    private String leader;
    private Long fatherDeptId;
    private Long companyId;
    private Instant time;
    private CompanyDto company;
    private List<DepartmentDto> departments;
/*    private boolean hasSonDepartments; // 新增字段
    private DepartmentDto parentDepartment;*/

    public List<DepartmentDto> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentDto> departments) {
        this.departments = departments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }



    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }


    public Long getFatherDeptId() {
        return fatherDeptId;
    }

    public void setFatherDeptId(Long fatherDeptId) {
        this.fatherDeptId = fatherDeptId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

/*
    public DepartmentDto DFS(Department department){
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setTime(department.getTime());
        for(Department dept: department.getSonDepartments()){
            departments.add(DFS(dept));
        }
        return dto;
    }*/
}
