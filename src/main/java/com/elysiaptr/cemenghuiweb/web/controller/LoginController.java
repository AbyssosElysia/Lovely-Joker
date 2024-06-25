package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.web.po.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//@RestController
//@RequestMapping("/api")
//public class LoginController {
//
//    @PostMapping("/login/username")
//    public Response login(@RequestBody Map<String, String> loginData, HttpServletRequest request) {
//        String username = loginData.get("username");
//        String password = loginData.get("password");
//        if ("admin".equals(username) && "password".equals(password)) {
//            request.getSession().setAttribute("user", username);
//            return Response.okResponse("commit");
//        } else {
//            return Response.notFoundResponse();
//        }
//    }
//
//}

