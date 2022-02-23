package com.example.swagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    //http://localhost:8080/swagger-ui/  <- 로가면 swagger ui로 간다.
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
