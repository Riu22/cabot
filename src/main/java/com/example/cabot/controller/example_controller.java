package com.example.cabot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class example_controller {
    @GetMapping("/hello")
    public String hello() {
        return HttpStatus.OK + " Hello, World!";
    }
}
