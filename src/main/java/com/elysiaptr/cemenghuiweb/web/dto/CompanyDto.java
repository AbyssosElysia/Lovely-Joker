package com.elysiaptr.cemenghuiweb.web.dto;

import com.elysiaptr.cemenghuiweb.web.po.ClassC;
import com.elysiaptr.cemenghuiweb.web.po.Department;
import com.elysiaptr.cemenghuiweb.web.po.News;
import com.elysiaptr.cemenghuiweb.web.po.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CompanyDto {
    private Long id;
    private String contact;//联系人
    private String logo;
    private String name;//企业名称
    private Long mobile;
    private String remark;
    private String adminName;//管理员
    private Instant time;


    private List<ClassC> classCS = new ArrayList<ClassC>();
    private List<User> users = new ArrayList<User>();
    private List<DepartmentDto> departments = new ArrayList<DepartmentDto>();
    private List<News> news = new ArrayList<News>();
    private List<DepartmentDto> departmentTrees = new ArrayList<>();

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public List<ClassC> getClassCS() {
        return classCS;
    }

    public void setClassCS(List<ClassC> classCS) {
        this.classCS = classCS;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }





    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }


    public List<DepartmentDto> getDepartmentTrees() {
        return departmentTrees;
    }

    public void setDepartmentTrees(List<DepartmentDto> departmentTrees) {
        this.departmentTrees = departmentTrees;
    }

    public List<DepartmentDto> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentDto> departments) {
        this.departments = departments;
    }
}
