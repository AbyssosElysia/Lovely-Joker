package com.elysiaptr.cemenghuiweb.web.repo;

import com.elysiaptr.cemenghuiweb.web.po.ClassC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassCRepository extends JpaRepository<ClassC, Long> {
    //List<ClassC> findAllById(List<Long> ids);
}
