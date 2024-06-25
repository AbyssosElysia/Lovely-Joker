package com.elysiaptr.cemenghuiweb.web.service.impl;

import com.elysiaptr.cemenghuiweb.web.dto.ClassCDto;
import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.ClassC;
import com.elysiaptr.cemenghuiweb.web.repo.ClassCRepository;
import com.elysiaptr.cemenghuiweb.web.service.ClassCService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassCServiceImpl implements ClassCService {
    @Autowired
    private ClassCRepository classCRepository;

    @Transactional
    @Override
    public ClassC saveClassC(ClassC classC) {
        return classCRepository.save(classC);
    }

    @Transactional
    @Override
    public ClassC updateClassC(Long id, ClassC classCDetails) {
        ClassC classC = classCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassC not found for this id :: " + id));

        classC.setName(classCDetails.getName());
        classC.setImage(classCDetails.getImage());
        classC.setIntroduction(classCDetails.getIntroduction());
        classC.setAuthor(classCDetails.getAuthor());
        classC.setCompany(classCDetails.getCompany());
        classC.setClassVideos(classCDetails.getClassVideos());

        return classCRepository.save(classC);
    }

    @Transactional
    @Override
    public void deleteClassC(Long id) {
        ClassC classC = classCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassC not found for this id :: " + id));
        classCRepository.delete(classC);
    }

    @Override
    public ClassC getClassCById(Long id) {
        return classCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassC not found for this id :: " + id));
    }

    @Override
    public List<ClassC> getAllClassCs() {
        return classCRepository.findAll();
    }

    @Override
    public Page<ClassC> getClassCsByPage(int page, int size) {
        return classCRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public ClassCDto toDto(ClassC classC) {
        return null;
    }

    @Override
    public ClassC toClass(ClassCDto classCDto) {
        return null;
    }
}