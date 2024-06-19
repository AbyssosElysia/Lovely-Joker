package com.elysiaptr.cemenghuiweb.po;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class MeetingUser {
    @EmbeddedId
    private MeetingUserId id;

    @MapsId("meetingId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ColumnDefault("1")
    @Column(name = "domain", nullable = false)
    private Byte domain;

    public MeetingUserId getId() {
        return id;
    }

    public void setId(MeetingUserId id) {
        this.id = id;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Byte getDomain() {
        return domain;
    }

    public void setDomain(Byte domain) {
        this.domain = domain;
    }

}