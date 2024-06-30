package com.elysiaptr.cemenghuiweb.web.controller;


import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.ClassCDto;
import com.elysiaptr.cemenghuiweb.web.dto.ClassVideoDto;
import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.po.ClassC;
import com.elysiaptr.cemenghuiweb.web.po.ClassVideo;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.service.ClassCService;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/classes/")
public class ClassController {
    @Autowired
    private ClassCService classCService;
    private CompanyService companyService;
    //增
    @PostMapping("/add")
    public R add(@RequestBody ClassCDto classCDto) {
        if (classCDto==null){
            return R.error().message("课程信息不能为空");
        }
        ClassC classC = new ClassC();
        classC.setName(classCDto.getName());
        classC.setImage(classCDto.getImage());
        classC.setAuthor(classCDto.getAuthor());
        classC.setIntroduction(classCDto.getIntroduction());
        long companyId = Long.parseLong(classCDto.getCompany_id());
        classC.setCompany(companyService.getCompanyById(companyId));
        ClassVideo classVideo = new ClassVideo();
        classVideo.setPath(classCDto.getClassVideos().getPath());
        classVideo.setOrder(classCDto.getClassVideos().getOrder());
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
        ClassC classC = new ClassC();
        classC.setName(classCDto.getName());
        classC.setImage(classCDto.getImage());
        classC.setAuthor(classCDto.getAuthor());
        classC.setIntroduction(classCDto.getIntroduction());
        long companyId = Long.parseLong(classCDto.getCompany_id());
        classC.setCompany(companyService.getCompanyById(companyId));
        ClassVideo classVideo = new ClassVideo();
        classVideo.setPath(classCDto.getClassVideos().getPath());
        classVideo.setOrder(classCDto.getClassVideos().getOrder());
        classVideo.setClassCField(classC);
        classC.setClassVideo(classVideo);
        classCService.updateClassC(classCDto.getId(),classC);
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
        List<ClassC> classCList=new ArrayList<>();
        return R.OK().data("classCList", classCList);
    }
}
