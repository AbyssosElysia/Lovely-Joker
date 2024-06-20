package com.elysiaptr.cemenghuiweb.service;

import com.elysiaptr.cemenghuiweb.po.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    Post savePost(Post post);

    Post updatePost(Long id, Post postDetails);

    void deletePost(Long id);

    Post getPostById(Long id);

    List<Post> getAllPosts();

    Page<Post> getPostsByPage(int page, int size);
}