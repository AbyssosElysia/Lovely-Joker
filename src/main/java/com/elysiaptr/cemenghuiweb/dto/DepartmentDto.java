package com.elysiaptr.cemenghuiweb.dto;

import java.time.Instant;

public class DepartmentDto {
    private Long id;
    private String name;
    private Long mobile;
    private String email;
    private Byte status;
    private Instant time;

    private String fatherDept_id;
    private String company_id;

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

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getFatherDept_id() {
        return fatherDept_id;
    }

    public void setFatherDept_id(String fatherDept_id) {
        this.fatherDept_id = fatherDept_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
}
