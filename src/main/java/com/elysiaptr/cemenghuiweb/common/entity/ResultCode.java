package com.elysiaptr.cemenghuiweb.common.entity;

public interface ResultCode {
    Integer OK = 200;
    Integer CREATED = 201;
    Integer NO_CONTENT = 204;
    Integer RESET_CONTENT = 205;
    Integer FORBIDDEN = 403;
    Integer NOT_FOUND = 404;
    Integer INTERNAL_SERVER_ERROR = 500;
    String OK_MSG = "OK";
    String CREATED_MSG = "Created";
    String NO_CONTENT_MSG = "No Content";
    String RESET_CONTENT_MSG = "Reset Content";
    String FORBIDDEN_MSG = "Forbidden";
    String NOT_FOUND_MSG = "Not Found";
    String INTERNAL_SERVER_ERROR_MSG = "Internal Server Error";
}
