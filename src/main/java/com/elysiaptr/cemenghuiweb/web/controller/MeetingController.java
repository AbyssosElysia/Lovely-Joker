package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.MeetingDto;
import com.elysiaptr.cemenghuiweb.web.po.Meeting;
import com.elysiaptr.cemenghuiweb.web.service.MeetingService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/meeting")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    private UserService userService;
    //新增
    @PostMapping("/add")
    public R addMeeting(@RequestBody MeetingDto meetingDto) {
        if (meetingDto==null){
            return R.error().message("会议信息不能为空");
        }
        Meeting meeting = new Meeting();
        meeting.setName(meetingDto.getName());
        meeting.setImage(meetingDto.getImage());
        meeting.setContent(meetingDto.getContent());
        meeting.setHolder(userService.getUserByUsername(meetingDto.getHolder()));
        meeting.setStartTime(meetingDto.getStartTime());
        meeting.setEndTime(meetingDto.getEndTime());
        meeting.setStatus(meetingDto.getStatus());
        meetingService.saveMeeting(meeting);
        return R.OK().data("提示", "新增会议成功");
    }
    //删除
    @PostMapping("/delete")
    public R deleteMeeting(@RequestBody MeetingDto meetingDto) {
        meetingService.deleteMeeting(meetingDto.getId());
        return R.OK().data("提示", "删除会议成功");
    }
    //修改
    @PostMapping("/update")
    public R updateMeeting(@RequestBody MeetingDto meetingDto) {
        Meeting meeting = new Meeting();
        meeting.setName(meetingDto.getName());
        meeting.setImage(meetingDto.getImage());
        meeting.setContent(meetingDto.getContent());
        meeting.setStartTime(meetingDto.getStartTime());
        meeting.setEndTime(meetingDto.getEndTime());
        meetingService.updateMeeting(meetingDto.getId(),meeting);
        return R.OK().data("提示", "修改信息成功");
    }
    //查找
    @GetMapping("/search_by_list")
    public R searchByList(@RequestParam(required = false)String name,
                          @RequestParam(required = false)String holder,
                          @RequestParam(required = false)Instant startTime){
        List<Meeting> meetingList=meetingService.getAllMeetings();
        if (name!=null){
            meetingList=meetingService.searchMeetingByName(name);
        }
        if (holder!=null){
            meetingList=meetingService.searchMeetingByHolder(holder);
        }
        if (startTime!=null){
            meetingList=meetingService.searchMeetingByStartTime(startTime);
        }
        return R.OK().data("meetingList", meetingList);

    }
    @GetMapping("/search_all")
    public R searchAll(){
        List<Meeting> meetingList=meetingService.getAllMeetings();
        return R.OK().data("meetingList", meetingList);
    }

}
