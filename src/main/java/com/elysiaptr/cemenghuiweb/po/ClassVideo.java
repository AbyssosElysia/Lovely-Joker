package com.elysiaptr.cemenghuiweb.po;

import java.io.Serializable;

public class ClassVideo implements Serializable {
    private int id;
    private int order;
    private String path;
    private String title;
    private int class_id;

    public ClassVideo(int id, int order, String path, String title, int class_id) {
        this.id = id;
        this.order = order;
        this.path = path;
        this.title = title;
        this.class_id = class_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
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

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "ClassVideo{" +
                "id=" + id +
                ", order=" + order +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", class_id=" + class_id +
                '}';
    }
}
