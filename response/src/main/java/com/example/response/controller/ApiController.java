package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    // TEXT
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    // JSON
    // req -> object mapper -> object  -> method -> object -> object mapper -> json  -> response
    @PostMapping("/json")
    public User json(@RequestBody User user){
        System.out.println(user.toString());
        return user; //200 OK
    }

    // ResponseEntity를 사용한다.
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user){
        /*
        OK(200, HttpStatus.Series.SUCCESSFUL, "OK"),
        CREATED(201, HttpStatus.Series.SUCCESSFUL, "Created"),
        ACCEPTED(202, HttpStatus.Series.SUCCESSFUL, "Accepted"),
         */

        //ResponseEntity를 사용해서 명확하게 값을 넣는 것이 좋다.
        //HttpStatus에 명확하게 넣어서 보낸다.
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
