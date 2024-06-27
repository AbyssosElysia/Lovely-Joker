package com.elysiaptr.cemenghuiweb.web.service.impl;

import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.ClassVideo;
import com.elysiaptr.cemenghuiweb.web.repo.ClassVideoRepository;
import com.elysiaptr.cemenghuiweb.web.service.ClassVideoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassVideoServiceImpl implements ClassVideoService {
    @Autowired
    private ClassVideoRepository classVideoRepository;

    @Transactional
    @Override
    public ClassVideo saveClassVideo(ClassVideo classVideo) {
        return classVideoRepository.save(classVideo);
    }

    @Transactional
    @Override
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
    @Override
    public void deleteClassVideo(Long id) {
        ClassVideo classVideo = classVideoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassVideo not found for this id :: " + id));
        classVideoRepository.delete(classVideo);
    }

    @Override
    public ClassVideo getClassVideoById(Long id) {
        return classVideoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClassVideo not found for this id :: " + id));
    }

    @Override
    public List<ClassVideo> getAllClassVideos() {
        return classVideoRepository.findAll();
    }

    @Override
    public Page<ClassVideo> getClassVideosByPage(int page, int size) {
        return classVideoRepository.findAll(PageRequest.of(page, size));
    }
}