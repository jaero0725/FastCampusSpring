package com.example.aop.controller;

import com.example.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        return id +" " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
        return user;
    }

    // End Point 들이 현업에서는 상당히 많을 수 있다. 로그를 찍는 걸 한곳으로 모은다.
    // @RequestBody, @RequestParam
}
