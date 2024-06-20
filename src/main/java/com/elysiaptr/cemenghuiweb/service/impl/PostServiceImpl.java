package com.elysiaptr.cemenghuiweb.service.impl;

import com.elysiaptr.cemenghuiweb.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.po.Post;
import com.elysiaptr.cemenghuiweb.repo.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl {
    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + id));

        post.setName(postDetails.getName());
        post.setUsers(postDetails.getUsers());
        post.setDepartments(postDetails.getDepartments());

        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + id));
        postRepository.delete(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + id));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Page<Post> getPostsByPage(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size));
    }
}