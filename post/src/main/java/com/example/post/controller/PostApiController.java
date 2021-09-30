package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {
    @PostMapping("/post")
    public void post(@RequestBody Map <String, Object> requestData){
        requestData.forEach((key, value) -> {
            System.out.println("key : " + key);
            System.out.println("value :" + value);
        });
    }
    /*
    {
        "account" : "user01",
            "email" : "steve@gmail.com",
            "address" : "집",
            "password" : "abcd1234",
            "phone_number" :"01092334574",
            "OTP" :"1234"
    }
    */
    //dto를 통해서 데이터를 받는다.
    @PostMapping("/post02")
    public void post(@RequestBody PostRequestDto postRequestDto){
        System.out.println(postRequestDto.toString());
    }
}
