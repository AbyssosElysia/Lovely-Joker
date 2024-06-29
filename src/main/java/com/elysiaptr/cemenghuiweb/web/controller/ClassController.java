package com.elysiaptr.cemenghuiweb.web.controller;


import com.elysiaptr.cemenghuiweb.web.service.ClassCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/classes/")
public class ClassController {
    @Autowired
    private ClassCService classCService;
    @GetMapping("/search_all")


}
