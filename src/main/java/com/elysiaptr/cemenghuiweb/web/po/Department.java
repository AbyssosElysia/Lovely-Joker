package com.elysiaptr.cemenghuiweb.web.po;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department", schema = "CeMengHui")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "manager", nullable = false, length = 60)
    private String manager;

    @Column(name = "mobile")
    private Long mobile;

    @Column(name = "email", length = 60)
    private String email;

    @ColumnDefault("0")
    @Column(name = "status", nullable = false)
    private Byte status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "father_dept_id")
    private Department fatherDept;

    @Column(name = "time", nullable = false)
    private Instant time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;



    @OneToMany(mappedBy = "fatherDept", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Department> sonDepartments;

    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<User>();

    @ManyToMany
    @JoinTable(
            name = "dept_post",
            joinColumns = @JoinColumn(name = "dept_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> posts = new ArrayList<>();

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

    public Department getFatherDept() {
        return fatherDept;
    }

    public void setFatherDept(Department fatherDept) {
        this.fatherDept = fatherDept;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<Department> getSonDepartments() {
        return sonDepartments;
    }

    public void setSonDepartments(List<Department> sonDepartments) {
        this.sonDepartments = sonDepartments;
    }
}