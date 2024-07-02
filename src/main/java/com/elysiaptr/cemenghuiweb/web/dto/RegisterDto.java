package com.elysiaptr.cemenghuiweb.web.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class RegisterDto {
    private String companyName;
    private String password;
    private Long mobile;
    private String captcha;
    private String userName;
    private String contact;
    private String email;
    private String gender;
    private Byte status;
    private Byte role;
}
