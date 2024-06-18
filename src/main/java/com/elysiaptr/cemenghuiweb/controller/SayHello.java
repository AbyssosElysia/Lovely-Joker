package com.elysiaptr.cemenghuiweb.controller;

import com.elysiaptr.cemenghuiweb.po.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHello {
    @RequestMapping("/hello")
    public Response sayHello() {
        return Response.okResponse("Hello Elysia!");
    }
}
