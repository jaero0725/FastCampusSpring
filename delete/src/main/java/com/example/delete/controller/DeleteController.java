package com.example.delete.controller;

import org.springframework.web.bind.annotation.*;
/*
    DELETE 메서드
    리소스의 삭제
    멱등함
    DataBody 사용하지 않는 것이 좋다.
 */

@RestController
@RequestMapping("/api")
public class DeleteController {

    //userId값을 받아서 삭제한다.
    //GET과 동작하지만, DELETE -> 리소스 삭제 . 리소스가 없더라도 200 값을 보내온다.
    //멱등하다.
    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId, @RequestParam String account){
        System.out.println("userid : " + userId);
        System.out.println("account : " + account);
    }
}
