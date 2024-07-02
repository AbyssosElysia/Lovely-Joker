package com.elysiaptr.cemenghuiweb.web.repo;

import com.elysiaptr.cemenghuiweb.web.po.ClassVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassVideoRepository extends JpaRepository<ClassVideo, Long> {
    ClassVideo findByOrder(int order);
}
