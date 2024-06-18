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

- **MySQL数据库名** cemenghui
- **Redis数据库名** 1

### 数据库初始化

```sql
CREATE DATABASE CeMengHui CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE CeMengHui;

-- 企业用户 User 表
CREATE TABLE User (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID 从1000000开始自增',
    username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
    name VARCHAR(60) NOT NULL COMMENT '姓名, 最大存储15个汉字',
    password VARCHAR(20) NOT NULL COMMENT '密码',
    mobile BIGINT COMMENT '手机',
    gender CHAR(4) NOT NULL DEFAULT '男' COMMENT '性别',
    email VARCHAR(40) NOT NULL COMMENT '邮箱, 使用QQ邮箱API发送验证码',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '用户状态, 0正常使用 1冻结',
    time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间, 格式YYYY-MM-DD HH:MM:SS 不可改变',
    role TINYINT NOT NULL DEFAULT 1 COMMENT '角色, 0管理员 1员工',
    remark VARCHAR(200) COMMENT '备注, 最大存储50个汉字',
    dept_id INT UNSIGNED COMMENT '部门 Department.id',
    post_id INT UNSIGNED COMMENT '岗位 Post.id',
    company_id INT UNSIGNED COMMENT '企业租户 Company.id'
);

-- 企业租户 Company 表
CREATE TABLE Company (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    contact VARCHAR(60) NOT NULL COMMENT '联系人姓名, 最大存储15个汉字',
    logo VARCHAR(100) COMMENT '企业标志, 存储图片位置',
    name VARCHAR(80) NOT NULL UNIQUE COMMENT '企业名称, 最大存储20个汉字',
    mobile BIGINT COMMENT '电话',
    time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间, 格式YYYY-MM-DD HH:MM:SS 不可改变',
    remark VARCHAR(150) COMMENT '备注, 最大存储50个汉字'
);

-- 超级管理员 SuperAdmin 表
CREATE TABLE SuperAdmin (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID 从100000开始自增',
    username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(20) NOT NULL COMMENT '密码'
);

-- 岗位 Post 表
CREATE TABLE Post (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    name VARCHAR(20) NOT NULL UNIQUE COMMENT '岗位名称'
);

-- 部门 Department 表
CREATE TABLE Department (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    name VARCHAR(60) NOT NULL UNIQUE COMMENT '部门名称, 最大存储15个汉字',
    mobile BIGINT COMMENT '联系电话',
    email VARCHAR(60) COMMENT '邮箱',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '状态, 0正常使用 1冻结',
    father_dept_id INT UNSIGNED COMMENT '父部门id, 为null时为顶级部门 Department.id',
    time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间, 格式YYYY-MM-DD HH:MM:SS 不可改变',
    company_id INT UNSIGNED COMMENT '企业租户 Company.id'
);

-- 资讯 News 表
CREATE TABLE News (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    image VARCHAR(100) COMMENT '概要图片, 存储图片位置',
    title VARCHAR(120) NOT NULL COMMENT '新闻标题, 最大存储30字',
    content TEXT NOT NULL COMMENT '新闻内容, 最大存储16384字',
    author VARCHAR(120) COMMENT '作者, 最大存储30字',
    introduction VARCHAR(480) COMMENT '简介, 最大存储120字',
    company_id INT UNSIGNED COMMENT '企业租户 Company.id'
);

-- 课程 Class 表
CREATE TABLE Class (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    name VARCHAR(120) NOT NULL COMMENT '课程名称, 最大存储30字',
    image VARCHAR(100) COMMENT '封面, 存储图片位置',
    introduction VARCHAR(1200) COMMENT '课程简介, 最大存储300字',
    author VARCHAR(120) COMMENT '作者, 最大存储30字',
    company_id INT UNSIGNED COMMENT '企业租户 Company.id'
);

-- 课程视频 ClassVideo 表
CREATE TABLE ClassVideo (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `order` SMALLINT NOT NULL COMMENT '课程排序',
    path VARCHAR(100) NOT NULL COMMENT '视频路径',
    title VARCHAR(100) NOT NULL COMMENT '标题, 最大存储25字',
    class_id INT UNSIGNED COMMENT '课程 Class.id'
);

-- 会议 Meeting 表
CREATE TABLE Meeting (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    name VARCHAR(160) NOT NULL COMMENT '会议名称, 最大存储40字',
    content VARCHAR(4000) COMMENT '会议内容, 最大存储1000字',
    image VARCHAR(100) COMMENT '会议封面, 存储图片位置',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '会议状态, 0未开始 1已开始 2已结束',
    start_time DATETIME NOT NULL COMMENT '会议开始时间, 格式YYYY-MM-DD HH:MM:SS',
    end_time DATETIME NOT NULL COMMENT '会议结束时间, 格式YYYY-MM-DD HH:MM:SS'
);

-- 会议用户 MeetingUser 表
CREATE TABLE MeetingUser (
    meeting_id INT UNSIGNED COMMENT '会议ID Meeting.id',
    user_id INT UNSIGNED COMMENT '用户ID User.id',
    domain TINYINT NOT NULL DEFAULT 1 COMMENT '是否为管理者 0是1否',
    PRIMARY KEY (meeting_id, user_id)
);

-- 部门岗位 DeptPost 表
CREATE TABLE DeptPost (
    dept_id INT UNSIGNED COMMENT '部门ID Department.id',
    post_id INT UNSIGNED COMMENT '岗位ID Post.id',
    PRIMARY KEY (dept_id, post_id)
);

-- 外键约束
ALTER TABLE User ADD CONSTRAINT FK_User_Dept FOREIGN KEY (dept_id) REFERENCES Department(id);
ALTER TABLE User ADD CONSTRAINT FK_User_Post FOREIGN KEY (post_id) REFERENCES Post(id);
ALTER TABLE User ADD CONSTRAINT FK_User_Company FOREIGN KEY (company_id) REFERENCES Company(id);

ALTER TABLE Department ADD CONSTRAINT FK_Dept_Company FOREIGN KEY (company_id) REFERENCES Company(id);

ALTER TABLE News ADD CONSTRAINT FK_News_Company FOREIGN KEY (company_id) REFERENCES Company(id);

ALTER TABLE Class ADD CONSTRAINT FK_Class_Company FOREIGN KEY (company_id) REFERENCES Company(id);

ALTER TABLE ClassVideo ADD CONSTRAINT FK_ClassVideo_Class FOREIGN KEY (class_id) REFERENCES Class(id);

ALTER TABLE MeetingUser ADD CONSTRAINT FK_MeetingUser_Meeting FOREIGN KEY (meeting_id) REFERENCES Meeting(id);
ALTER TABLE MeetingUser ADD CONSTRAINT FK_MeetingUser_User FOREIGN KEY (user_id) REFERENCES User(id);

ALTER TABLE DeptPost ADD CONSTRAINT FK_DeptPost_Dept FOREIGN KEY (dept_id) REFERENCES Department(id);
ALTER TABLE DeptPost ADD CONSTRAINT FK_DeptPost_Post FOREIGN KEY (post_id) REFERENCES Post(id);

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
spring.jpa.open-in-view=false

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=yoursqlport(default:6379)
spring.data.redis.database=1
```

## 文件存储路径

- **image** ~/resources/image/*
- **video** ~/resources/video/*
