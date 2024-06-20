package com.elysiaptr.cemenghuiweb.service.impl;

import com.elysiaptr.cemenghuiweb.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.po.Meeting;
import com.elysiaptr.cemenghuiweb.repo.MeetingRepository;
import com.elysiaptr.cemenghuiweb.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl {
    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Meeting createMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    @Transactional
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
    public void deleteMeeting(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found for this id :: " + id));
        meetingRepository.delete(meeting);
    }

    public Meeting getMeetingById(Long id) {
        return meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found for this id :: " + id));
    }

    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    public Page<Meeting> getMeetingsByPage(int page, int size) {
        return meetingRepository.findAll(PageRequest.of(page, size));
    }

    /*public List<User> getUsersByMeetingId(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found for this id :: " + meetingId));
        return meeting.getUsers();
    }

    public void addUserToMeeting(Long meetingId, Long userId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found for this id :: " + meetingId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        meeting.getUsers().add(user);
        meetingRepository.save(meeting);
    }

    public void removeUserFromMeeting(Long meetingId, Long userId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting not found for this id :: " + meetingId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        meeting.getUsers().remove(user);
        meetingRepository.save(meeting);
    }*/
}