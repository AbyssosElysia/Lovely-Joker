package com.elysiaptr.cemenghuiweb.web.controller;


import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.*;
import com.elysiaptr.cemenghuiweb.web.po.*;
import com.elysiaptr.cemenghuiweb.web.service.ClassCService;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.impl.ClassCServiceImpl;
import com.elysiaptr.cemenghuiweb.web.service.impl.ClassVideoServiceImpl;
import com.elysiaptr.cemenghuiweb.web.service.impl.CompanyServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/open_api/classes")
public class ClassController {
    @Autowired
    private ClassCService classCService;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;
    @Autowired
    private ClassCServiceImpl classCServiceImpl;
    @Autowired
    private ClassVideoServiceImpl classVideoServiceImpl;

    //增
    @PostMapping("/add")
    public R add(@RequestBody ClassCDto classCDto) {
        if (classCDto==null){
            return R.error().message("课程信息不能为空");
        }
        ClassC classC = new ClassC();
        try{
            BeanUtils.copyProperties(classCDto,classC);
            classC.setCompany(companyServiceImpl.getCompanyById(classCDto.getCompany_id()));
            classC.setClassVideo(classVideoServiceImpl.getClassVideoByOrder(classCDto.getClassVideoOrder()));
        }catch (Exception e){
            e.printStackTrace();
        }

      /*  ClassVideo classVideo = new ClassVideo();
        classVideo.setPath(classCDto.getClassVideoPath());
        classVideo.setOrder((short)classCDto.getClassVideoOrder());
        classVideo.setTitle(classCDto.getClassVideoTitle());
        classVideo.setId(classCDto.getId());
        classVideo.setClassCField(classC);
        classC.setClassVideo(classVideo);*/
        classCService.saveClassC(classC);
        return R.OK().data("提示", "新增课程成功");

    }
    //删除
    @PostMapping("/delete")
    public R deleteItems(@RequestBody ListDto listDto) {
        List<ItemDto> items = listDto.getItems();
        if (items != null && !items.isEmpty()) {
            for (ItemDto item : items) {
                ClassC existingItem = classCService.getClassCById(item.getId());
                if (existingItem != null && existingItem.getName().equals(item.getName())) {
                    // 调用服务层删除对应的 item
                    classCService.deleteClassC(item.getId());
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
    public R update(@RequestBody ClassCDto classCDto){
        classCService.updateClassC(classCDto.getId(),classCDto);
        return R.OK().data("提示", "修改信息成功");
    }
    @GetMapping("/search_by_list")
    public R searchByList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String introduction,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {


        // 先进行筛选
        List<ClassC> classCList = classCService.getAllClassCs();
       if(name!=null){
           classCList=classCService.searchClassByName(name);
       }
       if(id!=null){
           classCList=classCService.searchClassById(id);
       }
       if(introduction!=null){
           classCList=classCService.searchClassByIntroduction(introduction);
       }
        // 再进行分页
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), classCList.size());
        List<ClassC> pageList = classCList.subList(start, end);

        List<ClassCDto> classCDtos = pageList.stream()
                .map(classC -> {
                    ClassCDto dto = new ClassCDto();
                    dto.setId(classC.getId());
                    dto.setName(classC.getName());
                    dto.setIntroduction(classC.getIntroduction());
                    return dto;
                })
                .collect(Collectors.toList());

        Page<ClassCDto> classCDtoPage = new PageImpl<>(classCDtos, pageable, classCList.size());

        return R.OK().data("classCList", classCDtoPage);
    }
    //查all
    @GetMapping("/search_page")
    public R searchPage(@RequestParam(required = false, defaultValue = "0") int page,
                        @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClassC> classCPage = classCService.getClassCsByPage(pageable.getPageNumber(),pageable.getPageSize());

        List<ClassCDto> classCDtos =classCPage.getContent().stream()
                .map(classC -> {
                    ClassCDto dto = new ClassCDto();
                    dto.setId(classC.getId());
                    dto.setName(classC.getName());
                    dto.setIntroduction(classC.getIntroduction());


                    return dto;
                })
                .collect(Collectors.toList());

        Page<ClassCDto> classCDtoPage = new PageImpl<>(classCDtos, pageable, classCPage.getTotalElements());

        return R.OK().data("classCList", classCDtoPage);
    }
}
