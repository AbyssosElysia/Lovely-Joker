package com.elysiaptr.cemenghuiweb.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {
    private int id;
    private String username;
    private String name;
    private String password;
    private long mobile;
    private String gender;
    private String email;
    private int status;
    private LocalDateTime time;
    private int role;
    private String remark;
    private int domain;
    private int dept_id;
    private int post_id;
    private int company_id;

    public User(int id, String username, String name, String password, long mobile, String gender, String email, int status, LocalDateTime time, int role, String remark, int domain,int dept_id, int post_id, int company_id) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.gender = gender;
        this.email = email;
        this.status = status;
        this.time = time;
        this.role = role;
        this.remark = remark;
        this.domain=domain;
        this.dept_id = dept_id;
        this.post_id = post_id;
        this.company_id = company_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDomain() {return domain;}

    public void setDomain(int domain) {this.domain = domain;}

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", mobile=" + mobile +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", time=" + time +
                ", role=" + role +
                ", remark='" + remark + '\'' +
                ", domain=" + domain +
                ", dept_id=" + dept_id +
                ", post_id=" + post_id +
                ", company_id=" + company_id +
                '}';
    }



}
