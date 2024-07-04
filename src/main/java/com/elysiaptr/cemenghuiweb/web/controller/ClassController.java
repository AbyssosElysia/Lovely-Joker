package com.elysiaptr.cemenghuiweb.web.controller;


import com.alibaba.excel.EasyExcel;
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
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import java.time.ZoneId;
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
    /*绝对路径
    @PostMapping("/add")
    public R add(@RequestPart("classCDto") ClassCDto classCDto, @RequestPart("classVideoFile") MultipartFile classVideoFile) {
        if (classCDto == null || classVideoFile == null || classVideoFile.isEmpty()) {
            return R.error().message("课程信息和视频文件不能为空");
        }

        // 检查文件类型
        if (!classVideoFile.getContentType().equals("video/mp4")) {
            return R.error().message("只允许上传MP4格式的视频");
        }

        ClassC classC = new ClassC();
        try {
            // 复制 DTO 属性到实体类
            BeanUtils.copyProperties(classCDto, classC);
            classC.setCompany(companyServiceImpl.getCompanyById(classCDto.getCompany_id()));
            classC.setClassVideo(classVideoServiceImpl.getClassVideoByOrder(classCDto.getClassVideoOrder()));

            // 保存文件
            String fileName = System.currentTimeMillis() + "_" + classVideoFile.getOriginalFilename();
            String filePath = "/path/to/upload/directory/" + fileName;
            File dest = new File(filePath);
            classVideoFile.transferTo(dest);

            // 设置视频文件路径到实体类
            classC.setClassVideoPath(filePath);

        } catch (Exception e) {
            e.printStackTrace();
            return R.error().message("课程新增失败");
        }

        classCService.saveClassC(classC);
        return R.OK().data("提示", "新增课程成功");
    }
*/
    /*相对路径
    import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/classC")
public class ClassCController {

    @Value("${upload.directory}")
    private String uploadDirectory;

    @PostMapping("/add")
    public R add(@RequestPart("classCDto") ClassCDto classCDto, @RequestPart("classVideoFile") MultipartFile classVideoFile) {
        if (classCDto == null || classVideoFile == null || classVideoFile.isEmpty()) {
            return R.error().message("课程信息和视频文件不能为空");
        }

        // 检查文件类型
        if (!classVideoFile.getContentType().equals("video/mp4")) {
            return R.error().message("只允许上传MP4格式的视频");
        }

        ClassC classC = new ClassC();
        try {
            // 复制 DTO 属性到实体类
            BeanUtils.copyProperties(classCDto, classC);
            classC.setCompany(companyServiceImpl.getCompanyById(classCDto.getCompany_id()));
            classC.setClassVideo(classVideoServiceImpl.getClassVideoByOrder(classCDto.getClassVideoOrder()));

            // 创建上传目录（如果不存在）
            File uploadDir = new File(uploadDirectory);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 保存文件
            String fileName = System.currentTimeMillis() + "_" + classVideoFile.getOriginalFilename();
            String filePath = uploadDirectory + File.separator + fileName;
            File dest = new File(filePath);
            classVideoFile.transferTo(dest);

            // 设置视频文件路径到实体类
            classC.setClassVideoPath(filePath);

        } catch (IOException e) {
            e.printStackTrace();
            return R.error().message("课程新增失败");
        }

        classCService.saveClassC(classC);
        return R.OK().data("提示", "新增课程成功");
    }
}
*/

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
           classCList=classCService.searchClassByName(name,classCList);
       }
       if(id!=null){
           classCList=classCService.searchClassById(id,classCList);
       }
       if(introduction!=null){
           classCList=classCService.searchClassByIntroduction(introduction,classCList);
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
    @GetMapping("/applet_search_page")
    public R searchAppletPage(@RequestParam(required = false, defaultValue = "0") int page,
                        @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClassC> classCPage = classCService.getClassCsByPage(pageable.getPageNumber(),pageable.getPageSize());

        List<ClassCDto> classCDtos =classCPage.getContent().stream()
                .map(classC -> {
                    ClassCDto dto = new ClassCDto();
                    dto.setId(classC.getId());
                    dto.setName(classC.getName());
                    dto.setIntroduction(classC.getIntroduction());
                    dto.setImage(classC.getImage());
                    dto.setAuthor(classC.getAuthor());
                    dto.setCompany_id(classC.getCompany().getId());


                    return dto;
                })
                .collect(Collectors.toList());

        Page<ClassCDto> classCDtoPage = new PageImpl<>(classCDtos, pageable, classCPage.getTotalElements());

        return R.OK().data("classCList", classCDtoPage);
    }
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
    @GetMapping("/applet_search_by_list")
    public R searchAppletByList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String introduction,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {


        // 先进行筛选
        List<ClassC> classCList = classCService.getAllClassCs();
        if(name!=null){
            classCList=classCService.searchClassByName(name,classCList);
        }
        if(id!=null){
            classCList=classCService.searchClassById(id,classCList);
        }
        if(introduction!=null){
            classCList=classCService.searchClassByIntroduction(introduction,classCList);
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
                    dto.setImage(classC.getImage());
                    dto.setAuthor(classC.getAuthor());
                    dto.setCompany_id(classC.getCompany().getId());
                    dto.setClassVideoPath(classC.getClassVideo().getPath());
                    dto.setClassVideoOrder(classC.getClassVideo().getOrder());
                    dto.setClassVideoTitle(classC.getClassVideo().getTitle());
                    return dto;
                })
                .collect(Collectors.toList());

        Page<ClassCDto> classCDtoPage = new PageImpl<>(classCDtos, pageable, classCList.size());

        return R.OK().data("classCList", classCDtoPage);
    }
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportCourses(@RequestParam List<Long> ids) {
        // 根据传入的 ID 列表获取课程信息
        List<ClassC> classCList = classCService.getClassesByIds(ids);

        // 将 ClassC 列表转换为 ClassCDto 列表
        List<ClassCDto> classCDtos = classCList.stream()
                .map(classC -> {
                    ClassCDto dto = new ClassCDto();
                    dto.setId(classC.getId());
                    dto.setName(classC.getName());
                    dto.setIntroduction(classC.getIntroduction());
                    return dto;
                })
                .collect(Collectors.toList());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            // EasyExcel 写入数据到 Excel 文件
            EasyExcel.write(outputStream)
                    .head(ClassCDto.class) // 设置表头
                    .sheet("Courses") // 设置 sheet 名称
                    .doWrite(classCDtos); // 写入数据

            byte[] excelBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "courses.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelBytes);

        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/search_by_id")
    public R searchClassById(@RequestParam(required = false) Long id) {

        ClassC classC=classCService.getClassCById(id);
        ClassCDto classCDto=new ClassCDto();
        classCDto.setId(classC.getId());
        classCDto.setName(classC.getName());
        classCDto.setIntroduction(classC.getIntroduction());
        classCDto.setImage(classC.getImage());
        classCDto.setAuthor(classC.getAuthor());
        classCDto.setCompany_id(classC.getCompany().getId());
        classCDto.setClassVideoPath(classC.getClassVideo().getPath());
        classCDto.setClassVideoOrder(classC.getClassVideo().getOrder());
        classCDto.setClassVideoTitle(classC.getClassVideo().getTitle());
        return R.OK().data("class", classCDto);
    }
}

