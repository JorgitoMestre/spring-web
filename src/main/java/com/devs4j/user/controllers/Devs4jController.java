package com.devs4j.user.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Devs4jController {

    @GetMapping
    public String helloWord(){
        return "Hello Word!";
    }

}
