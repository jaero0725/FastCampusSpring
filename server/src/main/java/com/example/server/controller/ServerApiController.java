package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    //Naver API 사용
    //https://developers.naver.com/docs/serviceapi/search/local/local.md#%EC%A7%80%EC%97%AD
    //https://openapi.naver.com/v1/search/local.xml?query=\xea\xb0\x88\xeb\xb9\x84\xec\xa7\x91&display=10&start=1&sort=random
    @GetMapping("/naver")
    public String naver(){

        //String query ="갈비집";
        //String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));     //utf-8로 인코딩
        // utf-8 URiComponetsBuilder에서 encode하면된다.

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query","투썸플레이스")
                .queryParam("display",10)
                .queryParam("start",1)
                .queryParam("sort", "random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        log.info("uri : {}", uri);

        RestTemplate restTemplate = new RestTemplate();

        //Header를 사용
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "?")
                .header("X-Naver-Client-Secret" , "?")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req,String.class);

        return result.getBody();
    }

    @GetMapping("/hello")
    public User hello(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int age){

        //echo
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(
                     HttpEntity<String> entity,
                     @RequestBody Req<User> user,
                     @PathVariable int userId ,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,        // Header값 꺼내기 위함. - @ReqeusetHeader
                     @RequestHeader("custom-header") String customHeader
                ){

        log.info("req : {}", entity.getBody());
        log.info("userId :{}, userName {}", userId, userName);
        log.info("authorization : {}, custom : {}", authorization,customHeader);
        log.info("client req : {}", user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setHttpBody(
               user.getHttpBody()
        );
        return response;
    }
}
