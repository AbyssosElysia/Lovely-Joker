package com.elysiaptr.cemenghuiweb.authentication.service;

import com.elysiaptr.cemenghuiweb.web.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserFromDB(String username) {
        if (username.equals("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
//            User testUser = new User();
//            testUser.setUserId(1001L);
//            testUser.setUsername("admin");
//            testUser.setRoleId("admin");
//            testUser.setPassword(passwordEncoder.encode("admin"));
//            return testUser;
            return user;
        }
        return null;
    }
}
