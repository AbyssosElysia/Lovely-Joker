package com.elysiaptr.cemenghuiweb.web.service;

import com.elysiaptr.cemenghuiweb.web.po.Meeting;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.List;

public interface MeetingService {
    Meeting saveMeeting(Meeting meeting);

    Meeting updateMeeting(Long id, Meeting meetingDetails);

    void deleteMeeting(Long id);

    Meeting getMeetingById(Long id);

    List<Meeting> getAllMeetings();

    Page<Meeting> getMeetingsByPage(int page, int size);

    List<Meeting> searchMeetingByName(String name);

    List<Meeting> searchMeetingByHolder(String holder);

    List<Meeting> searchMeetingByStartTime(Instant startTime);
}