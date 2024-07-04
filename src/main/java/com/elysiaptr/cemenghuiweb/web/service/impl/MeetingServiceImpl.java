package com.elysiaptr.cemenghuiweb.web.service.impl;

import com.elysiaptr.cemenghuiweb.web.dto.MeetingDto;
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
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Transactional
    @Override
    public Meeting saveMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    @Transactional
    @Override
    public Meeting updateMeeting(Long id, MeetingDto meetingDetails) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found for this id :: " + id));
        if(meetingDetails.getName()!=null){
            meeting.setName(meetingDetails.getName());
        }
        if(meetingDetails.getStartTime()!=null){
            meeting.setStartTime(Instant.parse(meetingDetails.getStartTime()));
        }
        if(meetingDetails.getEndTime()!=null){
            meeting.setEndTime(Instant.parse(meetingDetails.getStartTime()));
        }
        if (meetingDetails.getImage()!=null){
            meeting.setImage(meetingDetails.getImage());
        }
        if (meetingDetails.getContent()!=null){
            meeting.setContent(meetingDetails.getContent());
        }
        if (meetingDetails.getStatus()!=null){
            meeting.setStatus(meetingDetails.getStatus());
        }
        if (meetingDetails.getHolder()!=null){
            meeting.setHolder(userServiceImpl.getUserByUsername(meetingDetails.getHolder()));
        }
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
    public List<Meeting> searchMeetingByName(String name,List<Meeting> meetingList) {
        return meetingList.stream()
                .filter(meeting -> meeting.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meeting> searchMeetingByHolder(String holder,List<Meeting> meetingList) {

        return meetingList.stream()
                .filter(meeting -> meeting.getHolder().getName().equals(holder))
                .collect(Collectors.toList());

    }
    @Override
    public List<Meeting> searchMeetingByStartTime(Instant startTime,List<Meeting> meetingList){
        return meetingList.stream()
                .filter(meeting -> meeting.getStartTime().equals(startTime))
                .collect(Collectors.toList());
    }
    @Override
    public List<Meeting> getMeetingsByIds(List<Long> ids) {
        return meetingRepository.findAllById(ids);
    }


}