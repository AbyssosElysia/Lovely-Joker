package com.elysiaptr.cemenghuiweb.authentication.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class CompanyReturnDto {
    private Long id;

    private String contact;

    private String logo;

    private String name;

    private Long mobile;

    private Instant time;

    private String remark;
}
