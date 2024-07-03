package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.ItemDto;
import com.elysiaptr.cemenghuiweb.web.dto.ListDto;
import com.elysiaptr.cemenghuiweb.web.dto.MeetingDto;
import com.elysiaptr.cemenghuiweb.web.dto.NewsDto;
import com.elysiaptr.cemenghuiweb.web.po.Meeting;
import com.elysiaptr.cemenghuiweb.web.po.News;
import com.elysiaptr.cemenghuiweb.web.service.MeetingService;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import com.elysiaptr.cemenghuiweb.web.service.impl.UserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/open_api/meeting")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    //新增
    @PostMapping("/add")
    public R addMeeting(@RequestBody MeetingDto meetingDto) {
        if (meetingDto==null){
            return R.error().message("会议信息不能为空");
        }
        Meeting meeting = new Meeting();
        BeanUtils.copyProperties(meetingDto, meeting);
        meeting.setHolder(userServiceImpl.getUserByUsername(meetingDto.getHolder()));
        meetingService.saveMeeting(meeting);
        return R.OK().data("提示", "新增会议成功");
    }
    //删除
    @PostMapping("/delete")
    public R deleteItems(@RequestBody ListDto listDto) {
        List<ItemDto> items = listDto.getItems();
        if (items != null && !items.isEmpty()) {
            for (ItemDto item : items) {
                Meeting existingItem = meetingService.getMeetingById(item.getId());
                if (existingItem != null && existingItem.getName().equals(item.getName())) {
                    // 调用服务层删除对应的 item
                    meetingService.deleteMeeting(item.getId());
                } else {
                    return R.error().data("提示", "ID 为 " + item.getId() + " 的项目不存在或名称不匹配");
                }
            }
            return R.OK().data("提示", "批量删除成功");
        } else {
            return R.error().data("提示", "请选择需要删除的项目");
        }
    }
    //修改
    @PostMapping("/update")
    public R updateMeeting(@RequestBody MeetingDto meetingDto) {
       /* meeting.setName(meetingDto.getName());
        meeting.setImage(meetingDto.getImage());
        meeting.setContent(meetingDto.getContent());
        meeting.setStartTime(meetingDto.getStartTime());
        meeting.setEndTime(meetingDto.getEndTime());*/
        meetingService.updateMeeting(meetingDto.getId(),meetingDto);
        return R.OK().data("提示", "修改信息成功");
    }
    @GetMapping("/search_by_list")
    public R searchByList(@RequestParam(required = false) String name,
                          @RequestParam(required = false) String holder,
                          @RequestParam(required = false) Instant startTime,
                          @RequestParam(required = false, defaultValue = "0") int page,
                          @RequestParam(required = false, defaultValue = "10") int size) {

        List<Meeting> meetingList = meetingService.getAllMeetings();

        if (name != null) {
            meetingList = meetingService.searchMeetingByName(name,meetingList);
        }
        if (holder != null) {
            meetingList = meetingService.searchMeetingByHolder(holder,meetingList);
        }
        if (startTime != null) {
            meetingList = meetingService.searchMeetingByStartTime(startTime,meetingList);
        }

        // 分页
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), meetingList.size());
        List<Meeting> pageList = meetingList.subList(start, end);

        List<MeetingDto> meetingDtos = pageList.stream()
                .map(meeting -> {
                    MeetingDto dto = new MeetingDto();
                    dto.setId(meeting.getId());
                    dto.setName(meeting.getName());
                    dto.setHolder(meeting.getHolder().getName());
                    dto.setStartTime(meeting.getStartTime());
                    return dto;
                })
                .collect(Collectors.toList());

        Page<MeetingDto> meetingDtoPage = new PageImpl<>(meetingDtos, pageable, meetingList.size());

        return R.OK().data("meetingList", meetingDtoPage);
    }
    @GetMapping("/search_page")
    public R searchPage(@RequestParam(required = false, defaultValue = "0") int page,
                        @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Meeting> meetingPage = meetingService.getMeetingsByPage(pageable.getPageNumber(),pageable.getPageSize());

        List<MeetingDto> meetingDtos = meetingPage.getContent().stream()
                .map(meeting -> {
                    MeetingDto dto = new MeetingDto();
                    dto.setId(meeting.getId());
                    dto.setName(meeting.getName());
                    dto.setHolder(meeting.getHolder().getName());
                    dto.setStartTime(meeting.getStartTime());
                    dto.setStatus(meeting.getStatus());
                    dto.setContent(meeting.getContent());

                    return dto;
                })
                .collect(Collectors.toList());

        Page<MeetingDto> meetingDtoPage = new PageImpl<>(meetingDtos, pageable, meetingPage.getTotalElements());

        return R.OK().data("meetingList", meetingDtoPage);
    }

}
