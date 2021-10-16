package com.example.aop.controller;

import com.example.aop.annotation.Decode;
import com.example.aop.annotation.Timer;
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

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {

        //db Logic
        Thread.sleep(1000 * 2);

    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user){
        System.out.println("put");
        System.out.println(user);

        return user;
    }

}
