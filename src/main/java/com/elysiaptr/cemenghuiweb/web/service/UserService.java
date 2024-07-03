package com.elysiaptr.cemenghuiweb.web.service;

import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.po.User;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface UserService {

    User saveUser(User user);

    User updateUser(Long id, UserDto userDetails);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> getAllUsers();

    Page<User> getUsersByPage(int page, int size);

    UserDto getUserDtoById(Long id);

    UserDto convertToDTO(User user);

    List<User> searchByUsername(String username,List<User> userList);

    List<User> searchByMobile(Long mobile,List<User> userList);

    List<User> searchByStatus(Integer status,List<User> userList);

    List<User> searchByTime(Instant time,List<User> userList);

    User getUserByUsername(String username);
    User getUserByName(String name);

    List<User> getUsersByIds(List<Long> userIds);
}

