package com.elysiaptr.cemenghuiweb.web.service.impl;

import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.repo.DepartmentRepository;
import com.elysiaptr.cemenghuiweb.web.repo.PostRepository;
import com.elysiaptr.cemenghuiweb.web.repo.UserRepository;
import com.elysiaptr.cemenghuiweb.web.service.UserService;
import com.elysiaptr.cemenghuiweb.web.utils.BCryptUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User saveUser(User user) {
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUser(Long id, UserDto userDetails) {
        //原来存的对象
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
/*        if(userDetails.getUsername()!=null){
            user.setUsername(userDetails.getUsername());
        }*/
        if (userDetails.getName()!=null){
            user.setName(userDetails.getName());
        }
        // 加密密码
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            String encryptedPassword = BCryptUtil.encode(userDetails.getPassword());
            user.setPassword(encryptedPassword);
        }
        if (userDetails.getMobile()!=null){
            user.setMobile(userDetails.getMobile());
        }
        if (userDetails.getEmail()!=null){
            user.setEmail(userDetails.getEmail());
        }
        if (userDetails.getGender()!=null){
            user.setGender(userDetails.getGender());
        }
        if (userDetails.getStatus()!=null){
            user.setStatus(userDetails.getStatus());
        }
        user.setTime(null);
        if (userDetails.getRole()!=null){
            user.setRole(userDetails.getRole());
        }
        if(userDetails.getRemark()!=null){
            user.setRemark(userDetails.getRemark());
        }
        if (departmentRepository.findByName(userDetails.getDepartmentName())!=null){
            user.setDept(departmentRepository.findByName(userDetails.getDepartmentName()));
        }
        if (postRepository.findByName(userDetails.getPostName())!=null){
            user.setPost(postRepository.findByName(userDetails.getPostName()));
        }

/*        user.setMeetingsBeHold(userDetails.getMeetingsBeHold());
        user.setMeetings(userDetails.getMeetings());*/

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


    public List<User> searchByUsername(String username,List<User> userList) {
        return userList.stream()
                .filter(user -> user.getUsername().contains(username))
                .collect(Collectors.toList());
    }
    @Override
    public List<User> searchByMobile(Long mobile,List<User> userList){

        return userList.stream()
                .filter(user -> user.getMobile().equals(mobile))
                .collect(Collectors.toList());
    }
    @Override
    public List<User> searchByStatus(Integer status,List<User> userList){

        return userList.stream()
                .filter(user -> Objects.equals(user.getStatus(), status.byteValue()))
                .collect(Collectors.toList());
    }
    public List<User> searchByTime(Instant time,List<User> userList) {

        return userList.stream()
                .filter(user -> user.getTime().equals(time))
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

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
}
