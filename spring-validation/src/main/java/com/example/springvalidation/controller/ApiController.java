package com.example.springvalidation.controller;

import com.example.springvalidation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/post")
    public Object user(@Valid @RequestBody User user, BindingResult bindingResult){


        //1. 옛날식 코드 data validation
        //if(!user.getPhoneNumber().equals("")){
        //    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        //}

        //2. 잘 binding 해주게 Spring Validation을 사용한다.

        //valid에 대한 결과가 BindingResult에 들어온다.
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();

            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field : " + field.getField());
                System.out.println("error : " + message);

                sb.append("\nfield : ").append(field.getField());
                sb.append("\nmessage : ").append(message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        //검증이 잘되면, 로직을 처리하면된다.
        System.out.println(user);
        return user;
    }

    /*
    {
      "name" : "홍길동",
      "age" : 10,
      "email" : "steve",
      "phoneNumber" : "01092334574"
    }
     */
}
