package com.example.springexception.controller;


import com.example.springexception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api")
@Validated
public class ApiController {

    @GetMapping("/user")
    public User get(
                    @Size(min = 1)
                    @RequestParam(required = false) String name,
                    @NotNull
                    @RequestParam(required = false) int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);

        //Error
        int a = 10 +age;

        return user;
    }

    @PostMapping("/user")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }


    //우선순위 : 내부 -> Global
    //특정 메서드의 예외를 잡을 떄 => Controller 안에서만 동작한다.
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity <?> methodArgumentNotValidException(Exception e){
        System.out.println("Internal Controller");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
