# Lovely-Joker

## 使用指南

### 环境配置

- **jdk** 21.0.3
- **Java** 17
- **Spring Boot** 3.3.0

### 组件

- **包管理工具** Maven
- **数据库** MySQL
- **内存数据库** Redis

### 数据库详情

- **MySQL数据库名** CeMengHui
- **Redis数据库名** 1

### 数据库初始化

```sql
CREATE DATABASE CeMengHui CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE CeMengHui;

-- 企业用户User
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(60) NOT NULL,
    password VARCHAR(400) NOT NULL,
    mobile BIGINT,
    gender VARCHAR(4) NOT NULL DEFAULT '男',
    email VARCHAR(40) NOT NULL,
    status TINYINT NOT NULL DEFAULT 0,
    time DATETIME NOT NULL,
    role TINYINT NOT NULL DEFAULT 1,
    remark VARCHAR(200),
    dept_id BIGINT,
    post_id BIGINT,
    company_id BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 企业租户Company
CREATE TABLE company (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contact VARCHAR(60) NOT NULL,
    logo VARCHAR(100),
    name VARCHAR(80) NOT NULL UNIQUE,
    mobile BIGINT,
    time DATETIME NOT NULL,
    remark VARCHAR(150)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 超级管理员SuperAdmin
CREATE TABLE super_admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 岗位Post
CREATE TABLE post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 部门Department
CREATE TABLE department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL UNIQUE,
    mobile BIGINT,
    email VARCHAR(60),
    status TINYINT NOT NULL DEFAULT 0,
    father_dept_id BIGINT,
    time DATETIME NOT NULL,
    company_id BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 资讯News
CREATE TABLE news (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    image VARCHAR(100),
    title VARCHAR(120) NOT NULL,
    content TEXT NOT NULL,
    author VARCHAR(120),
    introduction VARCHAR(480),
    company_id BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 课程Class
CREATE TABLE classc (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    image VARCHAR(100),
    introduction VARCHAR(1200),
    author VARCHAR(120),
    company_id BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 课程视频ClassVideo
CREATE TABLE class_video (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `order` SMALLINT NOT NULL,
    path VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    class_id BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 会议Meeting
CREATE TABLE meeting (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(160) NOT NULL,
    content VARCHAR(4000),
    image VARCHAR(100),
    status TINYINT NOT NULL DEFAULT 0,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    holder BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- MeetingUser
CREATE TABLE meeting_user (
    meeting_id BIGINT,
    user_id BIGINT,
    domain TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (meeting_id, user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- DeptPost
CREATE TABLE dept_post (
    dept_id BIGINT,
    post_id BIGINT,
    PRIMARY KEY (dept_id, post_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Adding foreign keys after creating all tables
ALTER TABLE user
ADD CONSTRAINT FK_User_Dept FOREIGN KEY (dept_id) REFERENCES department(id),
ADD CONSTRAINT FK_User_Post FOREIGN KEY (post_id) REFERENCES post(id),
ADD CONSTRAINT FK_User_Company FOREIGN KEY (company_id) REFERENCES company(id);

ALTER TABLE department
ADD CONSTRAINT FK_Dept_FatherDept FOREIGN KEY (father_dept_id) REFERENCES department(id),
ADD CONSTRAINT FK_Dept_Company FOREIGN KEY (company_id) REFERENCES company(id);

ALTER TABLE news
ADD CONSTRAINT FK_News_Company FOREIGN KEY (company_id) REFERENCES company(id);

ALTER TABLE classc
ADD CONSTRAINT FK_Class_Company FOREIGN KEY (company_id) REFERENCES company(id);

ALTER TABLE class_video
ADD CONSTRAINT FK_ClassVideo_Class FOREIGN KEY (class_id) REFERENCES classc(id);

ALTER TABLE meeting
ADD  CONSTRAINT FK_Meeting_Holder FOREIGN KEY (holder) REFERENCES user(id);

ALTER TABLE meeting_user
ADD CONSTRAINT FK_MeetingUser_Meeting FOREIGN KEY (meeting_id) REFERENCES meeting(id),
ADD CONSTRAINT FK_MeetingUser_User FOREIGN KEY (user_id) REFERENCES user(id);

ALTER TABLE dept_post
ADD CONSTRAINT FK_DeptPost_Dept FOREIGN KEY (dept_id) REFERENCES department(id),
ADD CONSTRAINT FK_DeptPost_Post FOREIGN KEY (post_id) REFERENCES post(id);

```

### Spring Boot依赖配置

- 请在src/main/resources/目录下修改application.properties配置文件

```properties
spring.application.name=CeMengHuiWeb

# MySQL
spring.datasource.url=jdbc:mysql://localhost:yoursqlport(default:3306)/CeMengHui
spring.datasource.username=yourusername(default:root)
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=true

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.database=1

# Jackson
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=GMT+8
```

## 文件存储路径

- **image** ~/resources/image/*
- **video** ~/resources/video/*
