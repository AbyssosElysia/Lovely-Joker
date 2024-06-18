package com.elysiaptr.cemenghuiweb.po;

import java.io.Serializable;

public class DeptPost implements Serializable {
    private int dept_id;
    private int post_id;

    public DeptPost(int dept_id, int post_id) {
        this.dept_id = dept_id;
        this.post_id = post_id;
    }

    public int getDepartment_id() {return dept_id;}

    public void setDepartment_id(int department_id) {this.dept_id = department_id;}

    public int getPost_id() {return post_id;}

    public void setPost_id(int post_id) {this.post_id = post_id;}

    @Override
    public String toString() {
        return "DeptPost{" +
                "department_id=" + dept_id +
                ", post_id=" + post_id +
                '}';
    }
}