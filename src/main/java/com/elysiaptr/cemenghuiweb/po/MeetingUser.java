package com.elysiaptr.cemenghuiweb.po;

import java.io.Serializable;

public class MeetingUser implements Serializable {
    private int meeting_id;
    private int user_id;

    public MeetingUser(int meeting_id, int user_id) {
        this.meeting_id = meeting_id;
        this.user_id = user_id;
    }

    public int getMeeting_id() {return meeting_id;}

    public void setMeeting_id(int meeting_id) {this.meeting_id = meeting_id;}

    public int getUser_id() {return user_id;}

    public void setUser_id(int user_id) {this.user_id = user_id;}

    @Override
    public String toString() {
        return "MeetingUser{" +
                "meeting_id=" + meeting_id +
                ", user_id=" + user_id +
                '}';
    }
}