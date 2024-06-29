package com.elysiaptr.cemenghuiweb.web.service;

import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.po.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User updateUser(Long id, User userDetails);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> getAllUsers();

    Page<User> getUsersByPage(int page, int size);

    UserDto getUserDtoById(Long id);

    UserDto convertToDTO(User user);

    User getUserByUsername(String username);
}

