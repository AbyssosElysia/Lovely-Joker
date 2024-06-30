package com.elysiaptr.cemenghuiweb.web.dto;

import java.util.List;

public class ClassCDto {
    private Long id;
    private String name;
    private String image;
    private String introduction;
    private String author;

    private String company_id;
    private ClassVideoDto classVideos;

 /*   private String classvideo.path;
    private int classvideo_order;*/

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

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public ClassVideoDto getClassVideos() {
        return classVideos;
    }

    public void setClassVideos(ClassVideoDto classVideos) {
        this.classVideos = classVideos;
    }
}
