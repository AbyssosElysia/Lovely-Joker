package com.elysiaptr.cemenghuiweb.service.impl;

import com.elysiaptr.cemenghuiweb.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.po.ClassC;
import com.elysiaptr.cemenghuiweb.repo.ClassCRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassCServiceImpl {
    @Autowired
    private ClassCRepository classCRepository;

    @Transactional
    public ClassC createClassC(ClassC classC) {
        return classCRepository.save(classC);
    }

    @Transactional
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
    public void deleteClassC(Long id) {
        ClassC classC = classCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassC not found for this id :: " + id));
        classCRepository.delete(classC);
    }

    public ClassC getClassCById(Long id) {
        return classCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassC not found for this id :: " + id));
    }

    public List<ClassC> getAllClassCs() {
        return classCRepository.findAll();
    }

    public Page<ClassC> getClassCsByPage(int page, int size) {
        return classCRepository.findAll(PageRequest.of(page, size));
    }
}