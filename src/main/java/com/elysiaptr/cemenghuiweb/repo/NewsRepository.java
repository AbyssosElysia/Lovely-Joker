package com.elysiaptr.cemenghuiweb.repo;

import com.elysiaptr.cemenghuiweb.po.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
