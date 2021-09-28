package com.example.hello.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path="/hello") // http://localhost:9090/api/get/hello
    public String getHello(){
        return "get Hello";
    }

    //예전에 쓰던 방식
    @RequestMapping( path = "/hi", method = RequestMethod.GET)  //get post put delete 모든 메소드 매핑
    public String getHi(){
        return "hi";
    }

    // 주소에 대문자 안씀
    // http://localhost:9090/api/get/path-variable/{name}
    @GetMapping("/path-variable/{id}")
    public String pathVariable(@PathVariable (name="id") String pathName){
        System.out.println("PathVariable : " + pathName);
        return pathName;
    }

    // 쿼리 파라미터
    //?key=value&key2=value2

    //http://localhost:9090/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path ="query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam ){ //Map으로 받는 경우 key를 알 수 없다.
        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach( entry ->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");
            sb.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
        });
        return sb.toString();
    }

    @GetMapping(path ="query-param02")
    public String queryParam02(
        @RequestParam String name,  //명시적으로 변수로 받는 법.
        @RequestParam String email,
        @RequestParam int age   //변수가 많아지면 빡셈 -> DTO형태로
    ){
        System.out.println(name+" " +email+" " + age);
        return name+" " +email+" " + age;
    }

    //객체를 DTO로 미리 정의해서 사용하는 것이 좋다.
    //API디자인을 할때 미리 고민해볼점.
    @GetMapping(path ="query-param03")
    public String queryParam03(UserRequest userRequest ){
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());
        return userRequest.toString();
    }
}
