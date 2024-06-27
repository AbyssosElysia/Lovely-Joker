package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.po.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open_api")
public class SayHello {
    @GetMapping("/hello")
    public R sayHello() {

        return R.OK().data("hello", "Hello World");
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
