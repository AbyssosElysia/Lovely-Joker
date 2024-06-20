package com.elysiaptr.cemenghuiweb.service.impl;

import com.elysiaptr.cemenghuiweb.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.po.ClassVideo;
import com.elysiaptr.cemenghuiweb.repo.ClassVideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassVideoServiceImpl {
    @Autowired
    private ClassVideoRepository classVideoRepository;

    @Transactional
    public ClassVideo createClassVideo(ClassVideo classVideo) {
        return classVideoRepository.save(classVideo);
    }

    @Transactional
    public ClassVideo updateClassVideo(Long id, ClassVideo classVideoDetails) {
        ClassVideo classVideo = classVideoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassVideo not found for this id :: " + id));

        classVideo.setOrder(classVideoDetails.getOrder());
        classVideo.setPath(classVideoDetails.getPath());
        classVideo.setTitle(classVideoDetails.getTitle());
        classVideo.setClassCField(classVideoDetails.getClassCField());

        return classVideoRepository.save(classVideo);
    }

    @Transactional
    public void deleteClassVideo(Long id) {
        ClassVideo classVideo = classVideoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassVideo not found for this id :: " + id));
        classVideoRepository.delete(classVideo);
    }

    public ClassVideo getClassVideoById(Long id) {
        return classVideoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassVideo not found for this id :: " + id));
    }

    public List<ClassVideo> getAllClassVideos() {
        return classVideoRepository.findAll();
    }

    public Page<ClassVideo> getClassVideosByPage(int page, int size) {
        return classVideoRepository.findAll(PageRequest.of(page, size));
    }
}