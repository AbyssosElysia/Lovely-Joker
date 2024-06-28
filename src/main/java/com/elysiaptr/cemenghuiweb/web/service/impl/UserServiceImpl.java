package com.elysiaptr.cemenghuiweb.web.service.impl;

import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.repo.UserRepository;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import com.elysiaptr.cemenghuiweb.web.utils.BCryptUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User saveUser(User user) {
        // 加密密码
        String encryptedPassword = BCryptUtil.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        user.setUsername(userDetails.getUsername());
        user.setName(userDetails.getName());
        // 加密密码
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            String encryptedPassword = BCryptUtil.encode(userDetails.getPassword());
            user.setPassword(encryptedPassword);
        }
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
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userRepository.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    }
    public List<User> searchByUsername(String username) {
        List<User> userList =null;
        return userList.stream()
                .filter(user -> user.getUsername().contains(username))
                .collect(Collectors.toList());
    }
    @Override
    public List<User> searchByMobile(Long mobile){
        List<User> userList =null;
        return userList.stream()
                .filter(user -> user.getMobile().equals(mobile))
                .collect(Collectors.toList());
    }
    @Override
    public List<User> searchByStatus(Integer status){
        List<User> userList =null;
        return userList.stream()
                .filter(user -> Objects.equals(user.getStatus(), status.byteValue()))
                .collect(Collectors.toList());
    }
    public List<User> searchByTime(LocalDateTime time) {
        List<User> userList =null;
        ZoneId zoneId = ZoneId.systemDefault(); // 获取当前系统时区
        return userList.stream()
                .filter(user -> user.getTime().atZone(zoneId).toLocalDateTime().equals(time))
                .collect(Collectors.toList());

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getUsersByPage(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public UserDto getUserDtoById(Long id) {
        return null;
    }

    @Override
    public UserDto convertToDTO(User user) {
        return null;
    }

}
