package com.elysiaptr.cemenghuiweb.web.po;

import jakarta.persistence.*;

@Entity
@Table(name = "class_video", schema = "CeMengHui")
public class ClassVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "`order`", nullable = false)
    private Short order;

    @Column(name = "path", nullable = false, length = 100)
    private String path;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassC classCField;*/

    @OneToOne
    @JoinColumn(name = "class_id")
    private ClassC classCField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getOrder() {
        return order;
    }

    public void setOrder(Short order) {
        this.order = order;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ClassC getClassField() {
        return classCField;
    }

    public void setClassField(ClassC classCField) {
        this.classCField = classCField;
    }

    public ClassC getClassCField() {
        return classCField;
    }

    public void setClassCField(ClassC classCField) {
        this.classCField = classCField;
    }

}