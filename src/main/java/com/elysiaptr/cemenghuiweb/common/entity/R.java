package com.elysiaptr.cemenghuiweb.common.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {

    private Boolean success; // 返回成功或失败的标志符
    private Integer code; // 返回状态码
    private String message; // 提示信息
    private Map<String, Object> data = new HashMap<>(); // 数据

    // 私有构造方法
    private R() {
    }

    // 200静态方法
    public static R OK() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.OK);
        r.setMessage(ResultCode.OK_MSG);
        return r;
    }

    // 201静态方法
    public static R created() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.CREATED);
        r.setMessage(ResultCode.CREATED_MSG);
        return r;
    }

    // 204静态方法
    public static R noContent() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.NO_CONTENT);
        r.setMessage(ResultCode.NO_CONTENT_MSG);
        return r;
    }

    // 205静态方法
    public static R resetContent() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.RESET_CONTENT);
        r.setMessage(ResultCode.RESET_CONTENT_MSG);
        return r;
    }

    // 403静态方法
    public static R forbidden() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.FORBIDDEN);
        r.setMessage(ResultCode.FORBIDDEN_MSG);
        return r;
    }

    // 404静态方法
    public static R notFound() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.NOT_FOUND);
        r.setMessage(ResultCode.NOT_FOUND_MSG);
        return r;
    }

    // 500静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        r.setMessage(ResultCode.INTERNAL_SERVER_ERROR_MSG);
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
