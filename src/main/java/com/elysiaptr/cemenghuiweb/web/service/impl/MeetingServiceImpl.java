package com.elysiaptr.cemenghuiweb.web.service.impl;

import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.Meeting;
import com.elysiaptr.cemenghuiweb.web.repo.MeetingRepository;
import com.elysiaptr.cemenghuiweb.web.service.MeetingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;

    @Transactional
    @Override
    public Meeting saveMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    @Transactional
    @Override
    public Meeting updateMeeting(Long id, Meeting meetingDetails) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found for this id :: " + id));

        meeting.setName(meetingDetails.getName());
        meeting.setContent(meetingDetails.getContent());
        meeting.setImage(meetingDetails.getImage());
        meeting.setStatus(meetingDetails.getStatus());
        meeting.setStartTime(meetingDetails.getStartTime());
        meeting.setEndTime(meetingDetails.getEndTime());
        meeting.setHolder(meetingDetails.getHolder());
        meeting.setUsers(meetingDetails.getUsers());

        return meetingRepository.save(meeting);
    }

    @Transactional
    @Override
    public void deleteMeeting(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found for this id :: " + id));
        meetingRepository.delete(meeting);
    }

    @Override
    public Meeting getMeetingById(Long id) {
        return meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found for this id :: " + id));
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    @Override
    public Page<Meeting> getMeetingsByPage(int page, int size) {
        return meetingRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Meeting> searchMeetingByName(String name) {
        List<Meeting> meetingList=null;
        return meetingList.stream()
                .filter(meeting -> meeting.getName().contains(name))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Meeting> searchMeetingByHolder(String holder) {
        List<Meeting> meetingList=null;
        return meetingList.stream()
                .filter(meeting -> meeting.getHolder().equals(holder))
                .collect(Collectors.toUnmodifiableList());

    }
    @Override
    public List<Meeting> searchMeetingByStartTime(Instant startTime){
        List<Meeting> meetingList=null;
        return meetingList.stream()
                .filter(meeting -> meeting.getStartTime().equals(startTime))
                .collect(Collectors.toUnmodifiableList());
    }


}