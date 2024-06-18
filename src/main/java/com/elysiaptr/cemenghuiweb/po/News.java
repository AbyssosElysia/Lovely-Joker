package com.elysiaptr.cemenghuiweb.po;

import java.io.Serializable;

public class News implements Serializable {
    private int id;
    private String image;
    private String title;
    private String content;
    private String author;
    private String introduction;
    private int company_id;

    public News(int id, String image, String title, String content, String author, String introduction, int company_id) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.content = content;
        this.author = author;
        this.introduction = introduction;
        this.company_id = company_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", introduction='" + introduction + '\'' +
                ", company_id=" + company_id +
                '}';
    }
}
