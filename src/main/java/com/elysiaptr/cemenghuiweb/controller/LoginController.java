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
    @PostMapping("/login")
    public Response login(String username, String password, HttpServletRequest request) {
        // 验证用户名和密码
        if ("admin".equals(username) && "password".equals(password)) {
            request.getSession().setAttribute("user", username);
            return Response.okResponse("true");
        } else {
            Map<String, Object> response = new HashMap<>();
            return Response.notFoundResponse();
        }
    }
}
