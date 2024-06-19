package com.elysiaptr.cemenghuiweb.po;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class DeptPostId implements java.io.Serializable {
    private static final long serialVersionUID = -4218934420978279277L;
    @Column(name = "dept_id", nullable = false)
    private Long deptId;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DeptPostId entity = (DeptPostId) o;
        return Objects.equals(this.deptId, entity.deptId) &&
                Objects.equals(this.postId, entity.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptId, postId);
    }

}