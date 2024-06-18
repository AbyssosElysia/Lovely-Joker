package com.elysiaptr.cemenghuiweb.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Department implements Serializable {
    private int id;
    private String name;
    private long mobile;
    private String email;
    private int status;
    private int father_dept_id;
    private LocalDateTime time;
    private int company_id;

    public Department(int id, String name, long mobile, String email, int status, int father_dept_id, LocalDateTime time, int company_id) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.status = status;
        this.father_dept_id = father_dept_id;
        this.time = time;
        this.company_id = company_id;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public long getMobile() {return mobile;}

    public void setMobile(long mobile) {this.mobile = mobile;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public int getStatus() {return status;}

    public void setStatus(int status) {this.status = status;}

    public int getFather_dept_id() {return father_dept_id;}

    public void setFather_dept_id(int father_dept_id) {this.father_dept_id = father_dept_id;}

    public LocalDateTime getTime() {return time;}

    public void setTime(LocalDateTime time) {this.time = time;}

    public int getCompany_id() {return company_id;}

    public void setCompany_id(int company_id) {this.company_id = company_id;}

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile=" + mobile +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", father_dept_id=" + father_dept_id +
                ", time=" + time +
                ", company_id=" + company_id +
                '}';
    }
}