package com.elysiaptr.cemenghuiweb.service.impl;

import com.elysiaptr.cemenghuiweb.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.po.Post;
import com.elysiaptr.cemenghuiweb.repo.PostRepository;
import com.elysiaptr.cemenghuiweb.service.PostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Transactional
    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    @Override
    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + id));

        post.setName(postDetails.getName());
        post.setUsers(postDetails.getUsers());
        post.setDepartments(postDetails.getDepartments());

        return postRepository.save(post);
    }

    @Transactional
    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + id));
        postRepository.delete(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + id));
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> getPostsByPage(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size));
    }
}