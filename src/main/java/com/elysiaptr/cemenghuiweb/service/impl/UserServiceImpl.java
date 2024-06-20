package com.elysiaptr.cemenghuiweb.service.impl;

import com.elysiaptr.cemenghuiweb.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.po.User;
import com.elysiaptr.cemenghuiweb.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        user.setUsername(userDetails.getUsername());
        user.setName(userDetails.getName());
        user.setPassword(userDetails.getPassword());
        user.setMobile(userDetails.getMobile());
        user.setGender(userDetails.getGender());
        user.setEmail(userDetails.getEmail());
        user.setStatus(userDetails.getStatus());
        user.setTime(userDetails.getTime());
        user.setRole(userDetails.getRole());
        user.setRemark(userDetails.getRemark());
        user.setDept(userDetails.getDept());
        user.setPost(userDetails.getPost());
        user.setCompany(userDetails.getCompany());
        user.setMeetingsBeHold(userDetails.getMeetingsBeHold());
        user.setMeetings(userDetails.getMeetings());

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userRepository.delete(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getUsersByPage(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }
}
