package com.elysiaptr.cemenghuiweb.service;

import com.elysiaptr.cemenghuiweb.po.ClassVideo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClassVideoService {
    ClassVideo saveClassVideo(ClassVideo classVideo);

    ClassVideo updateClassVideo(Long id, ClassVideo classVideoDetails);

    void deleteClassVideo(Long id);

    ClassVideo getClassVideoById(Long id);

    List<ClassVideo> getAllClassVideos();

    Page<ClassVideo> getClassVideosByPage(int page, int size);
}