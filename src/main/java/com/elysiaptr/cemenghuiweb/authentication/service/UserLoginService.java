package com.elysiaptr.cemenghuiweb.authentication.service;

import com.elysiaptr.cemenghuiweb.authentication.dto.LoginUserDto;
import com.elysiaptr.cemenghuiweb.web.po.User;

public interface UserLoginService {
    String login(LoginUserDto user);
}
