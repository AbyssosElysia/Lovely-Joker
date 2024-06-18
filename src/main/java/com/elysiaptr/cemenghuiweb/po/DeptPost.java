package com.elysiaptr.cemenghuiweb.po;

import java.io.Serializable;

public class DeptPost implements Serializable {
    private int department_id;
    private int post_id;

    public DeptPost(int department_id, int post_id) {
        this.department_id = department_id;
        this.post_id = post_id;
    }

    public int getDepartment_id() {return department_id;}

    public void setDepartment_id(int department_id) {this.department_id = department_id;}

    public int getPost_id() {return post_id;}

    public void setPost_id(int post_id) {this.post_id = post_id;}

    @Override
    public String toString() {
        return "DeptPost{" +
                "department_id=" + department_id +
                ", post_id=" + post_id +
                '}';
    }
}