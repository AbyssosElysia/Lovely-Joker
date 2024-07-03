package com.elysiaptr.cemenghuiweb.web.service;

import com.elysiaptr.cemenghuiweb.web.dto.ClassCDto;
import com.elysiaptr.cemenghuiweb.web.po.ClassC;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClassCService {
    ClassC saveClassC(ClassC classC);

    ClassC updateClassC(Long id, ClassCDto classCDetails);

    void deleteClassC(Long id);

    ClassC getClassCById(Long id);

    List<ClassC> getAllClassCs();

    Page<ClassC> getClassCsByPage(int page, int size);

    List<ClassC> searchClassByName(String name,List<ClassC> classCList);

    List<ClassC> searchClassById(Long id,List<ClassC> classCList);

    ClassCDto toDto(ClassC classC);

    ClassC toClass(ClassCDto classCDto);

    List<ClassC> searchClassByIntroduction(String introduction,List<ClassC> classCList);

    List<ClassC> getClassesByIds(List<Long> ids);
}