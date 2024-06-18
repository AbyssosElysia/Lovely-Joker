package com.elysiaptr.cemenghuiweb.po;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Company implements Serializable {
    private int id;
    private String contact;
    private String logo;
    private String name;
    private long mobile;
    private LocalDateTime time;
    private String remark;

    public Company(int id, String contact, String logo, String name, long mobile, LocalDateTime time, String remark) {
        this.id = id;
        this.contact = contact;
        this.logo = logo;
        this.name = name;
        this.mobile = mobile;
        this.time = time;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", contact='" + contact + '\'' +
                ", logo='" + logo + '\'' +
                ", name='" + name + '\'' +
                ", mobile=" + mobile +
                ", time=" + time +
                ", remark='" + remark + '\'' +
                '}';
    }
}
