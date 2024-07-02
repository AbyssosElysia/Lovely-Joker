package com.elysiaptr.cemenghuiweb.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.Meeting;
import com.elysiaptr.cemenghuiweb.web.repo.MeetingRepository;
import com.elysiaptr.cemenghuiweb.web.service.impl.MeetingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MeetingServiceTest {

    @Mock
    private MeetingRepository meetingRepository;

    @InjectMocks
    private MeetingServiceImpl meetingServiceImpl;

    @Test
    void testDeleteMeetingSuccess() {
        Long meetingId = 1L;
        Meeting meeting = new Meeting();
        meeting.setId(meetingId);

        when(meetingRepository.findById(meetingId)).thenReturn(Optional.of(meeting));

        meetingServiceImpl.deleteMeeting(meetingId);

        verify(meetingRepository, times(1)).findById(meetingId);
        verify(meetingRepository, times(1)).delete(meeting);
    }

    @Test
    void testDeleteMeetingNotFound() {
        Long meetingId = 1L;

        when(meetingRepository.findById(meetingId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            meetingServiceImpl.deleteMeeting(meetingId);
        });

        String expectedMessage = "Meeting not found for this id :: " + meetingId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(meetingRepository, times(1)).findById(meetingId);
        verify(meetingRepository, times(0)).delete(any(Meeting.class));
    }
}
