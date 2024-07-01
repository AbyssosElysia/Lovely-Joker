package com.elysiaptr.cemenghuiweb.web.repo;

import com.elysiaptr.cemenghuiweb.web.po.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByName(String name);
}
