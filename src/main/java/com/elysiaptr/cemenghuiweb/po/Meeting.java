package com.elysiaptr.cemenghuiweb.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Meeting implements Serializable {
    private int id;
    private String name;
    private String content;
    private String image;
    private int status;
    private LocalDateTime start_time;
    private LocalDateTime end_time;

    public Meeting(int id, String name, String content, String image, int status, LocalDateTime startTime, LocalDateTime end_time) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.image = image;
        this.status = status;
        this.start_time = startTime;
        this.end_time = end_time;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {this.image = image;}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return start_time;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.start_time = startTime;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }



    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}
