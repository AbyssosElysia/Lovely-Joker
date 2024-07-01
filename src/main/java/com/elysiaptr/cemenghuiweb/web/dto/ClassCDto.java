package com.elysiaptr.cemenghuiweb.web.dto;

import java.util.List;

public class ClassCDto {
    private Long id;
    private String name;
    private String image;
    private String introduction;
    private String author;

    private Long company_id;
   /* private ClassVideoDto classVideos;*/

    private String classVideoPath;
    private int classVideoOrder;
    private int classVideoId;
    private String classVideoTitle;

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





    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public String getClassVideoPath() {
        return classVideoPath;
    }

    public void setClassVideoPath(String classVideoPath) {
        this.classVideoPath = classVideoPath;
    }

    public int getClassVideoOrder() {
        return classVideoOrder;
    }

    public void setClassVideoOrder(int classVideoOrder) {
        this.classVideoOrder = classVideoOrder;
    }

    public int getClassVideoId() {
        return classVideoId;
    }

    public void setClassVideoId(int classVideoId) {
        this.classVideoId = classVideoId;
    }

    public String getClassVideoTitle() {
        return classVideoTitle;
    }

    public void setClassVideoTitle(String classVideoTitle) {
        this.classVideoTitle = classVideoTitle;
    }
}
