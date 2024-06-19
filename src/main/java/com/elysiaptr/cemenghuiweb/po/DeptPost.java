package com.elysiaptr.cemenghuiweb.po;

import jakarta.persistence.*;

@Entity
public class DeptPost {
    @EmbeddedId
    private DeptPostId id;

    @MapsId("deptId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dept_id", nullable = false)
    private Department dept;

    @MapsId("postId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public DeptPostId getId() {
        return id;
    }

    public void setId(DeptPostId id) {
        this.id = id;
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

}