package com.elysiaptr.cemenghuiweb.authentication.dto;

import lombok.Data;

@Data
public class LoginUserDto {
    private String username;
    private String password;
    private Long id;
}
