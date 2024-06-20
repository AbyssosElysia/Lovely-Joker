package com.elysiaptr.cemenghuiweb.repo;

import com.elysiaptr.cemenghuiweb.po.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
