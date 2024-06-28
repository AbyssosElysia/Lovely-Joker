package com.elysiaptr.cemenghuiweb.authentication.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
