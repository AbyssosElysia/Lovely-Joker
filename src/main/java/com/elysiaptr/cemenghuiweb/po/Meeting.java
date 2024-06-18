package com.elysiaptr.cemenghuiweb.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Meeting implements Serializable {
    private int id;
    private String name;
    private String content;
    private String image;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int userId;

    public Meeting(int id, String name, String content, String image, String status, LocalDateTime startTime, LocalDateTime endTime, int userId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.image = image;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
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

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", userId=" + userId +
                '}';
    }
}
