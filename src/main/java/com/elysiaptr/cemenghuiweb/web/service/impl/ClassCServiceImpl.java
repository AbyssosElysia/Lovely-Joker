package com.elysiaptr.cemenghuiweb.web.service.impl;

import com.elysiaptr.cemenghuiweb.web.dto.ClassCDto;
import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.ClassC;
import com.elysiaptr.cemenghuiweb.web.po.ClassVideo;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.repo.ClassCRepository;
import com.elysiaptr.cemenghuiweb.web.service.ClassCService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassCServiceImpl implements ClassCService {
    @Autowired
    private ClassCRepository classCRepository;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;
    @Autowired
    private ClassVideoServiceImpl classVideoServiceImpl;

    @Transactional
    @Override
    public ClassC saveClassC(ClassC classC) {
        // 保存 ClassC 对象
        System.out.println(classC);
        return classCRepository.save(classC);
    }

    @Transactional
    @Override
    public ClassC updateClassC(Long id, ClassCDto classCDetails) {
        ClassC classC = classCRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassC not found for this id :: " + id));
        if(classCDetails.getName()!=null){
            classC.setName(classCDetails.getName());
        }
        if(classCDetails.getImage()!=null){
            classC.setImage(classCDetails.getImage());
        }
        if (classCDetails.getIntroduction()!=null){
            classC.setIntroduction(classCDetails.getIntroduction());
        }
        if(classCDetails.getAuthor()!=null){
            classC.setAuthor(classCDetails.getAuthor());
        }
        if(classCDetails.getCompany_id()!=null){
            classC.setCompany(companyServiceImpl.getCompanyById(classCDetails.getCompany_id()));
        }

        if(classCDetails.getClassVideoOrder()!=0&&classCDetails.getClassVideoPath()!=null){
            ClassVideo classVideo = new ClassVideo();
            classVideo.setPath(classCDetails.getClassVideoPath());
            classVideo.setOrder((short)classCDetails.getClassVideoOrder());
            classVideo.setClassCField(classC);
            classC.setClassVideo(classVideo);
        }
        if(classCDetails.getCompany_id()!=null){
            classC.setCompany(companyServiceImpl.getCompanyById(classCDetails.getCompany_id()));
        }




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

    @Override
    public  List<ClassC> searchClassByName(String name,List<ClassC> classCList){

        return classCList.stream()
                .filter(classC -> classC.getName().contains(name))
                .collect(Collectors.toList());
    }
    @Override
    public List<ClassC> searchClassById(Long id,List<ClassC> classCList){

        return classCList.stream()
                .filter(classC -> classC.getId().equals(id))
                .collect(Collectors.toList());
    }
    @Override
    public  List<ClassC> searchClassByIntroduction(String introduction,List<ClassC> classCList){

        return classCList.stream()
                .filter(classC -> classC.getIntroduction().equals(introduction))
                .collect(Collectors.toList());
    }
    @Override
    public List<ClassC> getClassesByIds(List<Long> ids) {
        return classCRepository.findAllById(ids);
    }
}