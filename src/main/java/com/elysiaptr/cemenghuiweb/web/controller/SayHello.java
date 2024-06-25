package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.web.po.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SayHello {
    @RequestMapping("/hello")
    public Response sayHello() {
        return Response.okResponse("Hello Elysia!");
    }

    @GetMapping("/")
    public Response home() {
        return Response.okResponse("root");
    }

    @GetMapping("/home")
    public Response homePage() {
        return Response.okResponse("home");
    }
}
