package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    //Page를 가져오기
    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    //이부분에서 JSON을 어떻게 내려주는건가?

    //1. ResponseEntity

    //2. ResponseBody
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User(); //java 11부터 사용됨.
        user.setName("steve");
        user.setAddress("seoul");
        return user;
    }

    //Page Controller 에서 ResponseBody를 사용하지 않는 것을 추천한다.
    //API Controller를 따로 만들어서 정의해서 서비스하는 것이 정확한 방법이다.
}
