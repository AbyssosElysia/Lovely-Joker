package com.elysiaptr.cemenghuiweb.service;

import com.elysiaptr.cemenghuiweb.po.ClassC;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClassCService {
    ClassC saveClassC(ClassC classC);

    ClassC updateClassC(Long id, ClassC classCDetails);

    void deleteClassC(Long id);

    ClassC getClassCById(Long id);

    List<ClassC> getAllClassCs();

    Page<ClassC> getClassCsByPage(int page, int size);
}