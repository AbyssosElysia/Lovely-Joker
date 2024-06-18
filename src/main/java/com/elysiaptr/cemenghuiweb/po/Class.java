package com.elysiaptr.cemenghuiweb.po;

import java.io.Serializable;

public class Class implements Serializable {
    private int id;
    private String name;
    private String image;
    private String introduction;
    private String author;
    private int company_id;

    public Class(int id, String name, String image, String introduction, String author, int company_id) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.introduction = introduction;
        this.author = author;
        this.company_id = company_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", introduction='" + introduction + '\'' +
                ", author='" + author + '\'' +
                ", company_id=" + company_id +
                '}';
    }
}
