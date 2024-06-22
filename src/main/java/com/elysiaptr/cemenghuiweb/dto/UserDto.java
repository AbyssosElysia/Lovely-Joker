package com.elysiaptr.cemenghuiweb.dto;

import com.elysiaptr.cemenghuiweb.po.Company;
import com.elysiaptr.cemenghuiweb.po.Department;
import com.elysiaptr.cemenghuiweb.po.Meeting;
import com.elysiaptr.cemenghuiweb.po.Post;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private String name;
    private Long mobile;
    private String gender;
    private String email;
    private Byte status;
    private Instant time;
    private Byte role;
    private String remark;
    private Department dept;
    private Post post;
    private Company company;
    private List<Meeting> meetingsBeHold = new ArrayList<>();
    private List<Meeting> meetings =new ArrayList<Meeting>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Meeting> getMeetingsBeHold() {
        return meetingsBeHold;
    }

    public void setMeetingsBeHold(List<Meeting> meetingsBeHold) {
        this.meetingsBeHold = meetingsBeHold;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }
}
