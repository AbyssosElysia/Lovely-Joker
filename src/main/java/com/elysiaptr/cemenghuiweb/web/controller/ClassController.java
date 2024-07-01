package com.elysiaptr.cemenghuiweb.web.controller;


import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.ClassCDto;
import com.elysiaptr.cemenghuiweb.web.dto.ClassVideoDto;
import com.elysiaptr.cemenghuiweb.web.dto.NewsDto;
import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.po.*;
import com.elysiaptr.cemenghuiweb.web.service.ClassCService;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.impl.CompanyServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/open_api/classes/")
public class ClassController {
    @Autowired
    private ClassCService classCService;
    private CompanyService companyService;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;

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
        }catch (Exception e){
            e.printStackTrace();
        }

        ClassVideo classVideo = new ClassVideo();
        classVideo.setPath(classCDto.getClassVideoPath());
        classVideo.setOrder((short)classCDto.getClassVideoOrder());
        classVideo.setTitle(classCDto.getClassVideoTitle());
        classVideo.setId(classCDto.getId());
        classVideo.setClassCField(classC);
        classC.setClassVideo(classVideo);
        classCService.saveClassC(classC);
        return R.OK().data("提示", "新增课程成功");

    }
    //删除
    @PostMapping("/delete")
    public R deleteClass(@RequestBody ClassCDto classCDto){
        classCService.deleteClassC(classCDto.getId());
        return R.OK().data("提示", "删除课程成功");
    }
    //修改
    @PostMapping("/update")
    public R update(@RequestBody ClassCDto classCDto){
        classCService.updateClassC(classCDto.getId(),classCDto);
        return R.OK().data("提示", "修改信息成功");
    }
    //查
    @GetMapping("/search_by_list")
    public R searchByList(@RequestParam(required = false)String name,
                          @RequestParam(required = false)Long id){
        List<ClassC> classCList=new ArrayList<>();
        if(name !=null){
            classCList=classCService.searchClassByName(name);
        }
        if (id!=null){
            classCList=classCService.searchClassById(id);
        }
        return R.OK().data("classCList", classCList);
    }
    //查all
    @GetMapping("/search_all")
    public R searchAllClasses() {
        List<ClassC> classList=classCService.getAllClassCs();
        List<ClassCDto> classCDtos = new ArrayList<>();
        for(ClassC classC:classList){
            ClassCDto dto=new ClassCDto();
            // dto.setId(news.getId());
           dto.setId(classC.getId());
           dto.setName(classC.getName());
           dto.setIntroduction(classC.getIntroduction());
            // dto.setCompany_name(news.getCompany().getName());
            classCDtos.add(dto);
        }
        return R.OK().data("classCList", classCDtos);
    }
}
