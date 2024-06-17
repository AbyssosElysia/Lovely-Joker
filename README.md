# Lovely-Joker
## 数据库初始化文档
-- 创建数据库
CREATE DATABASE IF NOT EXISTS enterprise;
USE enterprise;

-- 创建企业用户表
CREATE TABLE enterprise_user (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL,
    username VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(60) NOT NULL,
    password VARCHAR(20) NOT NULL,
    mobile CHAR(11),
    gender CHAR(4) NOT NULL,
    email VARCHAR(40) NOT NULL,
    status TINYINT NOT NULL,
    time DATETIME NOT NULL,
    role TINYINT UNSIGNED,
    remark VARCHAR(150)
);

-- 创建企业租户表
CREATE TABLE enterprise_tenant (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    contact VARCHAR(60) NOT NULL,
    logo VARCHAR(100),
    name VARCHAR(80) NOT NULL,
    mobile CHAR(11),
    time DATETIME NOT NULL,
    remark VARCHAR(150)
);

-- 创建超级管理员表
CREATE TABLE super_admin (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL,
    name VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL
);

-- 创建岗位表
CREATE TABLE position (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

-- 创建部门表
CREATE TABLE department (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    mobile CHAR(11),
    email VARCHAR(40) NOT NULL,
    status TINYINT NOT NULL,
    father INT,
    time DATETIME NOT NULL,
    FOREIGN KEY (father) REFERENCES department(id)
);

-- 创建资讯表
CREATE TABLE news (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    image VARCHAR(100),
    title VARCHAR(120) NOT NULL,
    content VARCHAR(65536) NOT NULL,
    author VARCHAR(120) NOT NULL,
    introduction VARCHAR(480) NOT NULL
);

-- 创建课程表
CREATE TABLE course (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    image VARCHAR(100),
    introduction VARCHAR(1200) NOT NULL,
    author VARCHAR(120) NOT NULL
);

-- 创建课程视频表
CREATE TABLE course_video (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `order` SMALLINT NOT NULL,
    path VARCHAR(100) NOT NULL
);

-- 创建会议表
CREATE TABLE meeting (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(160) NOT NULL,
    content VARCHAR(4000) NOT NULL,
    founder VARCHAR(60) NOT NULL
);

## 文件存储路径
image: ~/resources/image/*
video: ~/resources/video/*

## nengyonglema