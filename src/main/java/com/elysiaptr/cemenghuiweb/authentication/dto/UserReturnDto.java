package com.elysiaptr.cemenghuiweb.authentication.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserReturnDto {
    private Long id;

    private String username;

    private String name;

    private Long mobile;

    private String gender;

    private String email;

    private Byte status;

    private Instant time;

    private Byte role;

    private String remark;

    private String departmentName;

    private String companyName;

    private String postName;

    private Long departmentId;

    private Long postId;

    private Long companyId;

}
