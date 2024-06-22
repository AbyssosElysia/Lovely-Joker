package com.elysiaptr.cemenghuiweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class MyController {

    @GetMapping("/protected/resource")
    @ResponseBody
    public String protectedResource() {
        return "This is a protected resource.";
    }

    @GetMapping("/login")
    @ResponseBody
    public String loginPage(HttpServletRequest request) {
        request.getSession().setAttribute("user", "testUser"); // 模拟登录
        return "Login successful, user set in session.";
    }
}
