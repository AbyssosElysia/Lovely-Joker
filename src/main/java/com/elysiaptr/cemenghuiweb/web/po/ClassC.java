package com.elysiaptr.cemenghuiweb.web.po;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classc", schema = "CeMengHui")
public class ClassC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Column(name = "image", length = 100)
    private String image;

    @Column(name = "introduction", length = 1200)
    private String introduction;

    @Column(name = "author", length = 120)
    private String author;

    @OneToOne(mappedBy = "classCField", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ClassVideo classVideo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

   /* @OneToMany(mappedBy = "classCField", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ClassVideo> classVideos = new ArrayList<ClassVideo>();*/

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public ClassVideo getClassVideo() {
        return classVideo;
    }

    public void setClassVideo(ClassVideo classVideo) {
        this.classVideo = classVideo;
    }
}