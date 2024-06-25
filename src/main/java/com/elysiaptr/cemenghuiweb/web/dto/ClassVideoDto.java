package com.elysiaptr.cemenghuiweb.web.dto;

public class ClassVideoDto {
    private Long id;
    private Short order;
    private String path;
    private String title;

    private String classCField_id;

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

    public String getClassCField_id() {
        return classCField_id;
    }

    public void setClassCField_id(String classCField_id) {
        this.classCField_id = classCField_id;
    }
}
