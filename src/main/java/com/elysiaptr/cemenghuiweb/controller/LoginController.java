package com.elysiaptr.cemenghuiweb.controller;

import com.elysiaptr.cemenghuiweb.po.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    public static class LoginRequest {
        public String username;
        public String password;
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        System.out.println("Login attempt: username=" + loginRequest.username + ", password=" + loginRequest.password);
        if ("admin".equals(loginRequest.username) && "password".equals(loginRequest.password)) {
            request.getSession().setAttribute("user", loginRequest.username);
            return Response.okResponse("true");
        } else {
            return Response.notFoundResponse();
        }
    }
}

