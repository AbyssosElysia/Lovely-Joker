package com.elysiaptr.cemenghuiweb.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class Company {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "contact", nullable = false, length = 60)
    private String contact;

    @Column(name = "logo", length = 100)
    private String logo;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "mobile")
    private Long mobile;

    @Column(name = "time", nullable = false)
    private Instant time;

    @Column(name = "remark", length = 150)
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}