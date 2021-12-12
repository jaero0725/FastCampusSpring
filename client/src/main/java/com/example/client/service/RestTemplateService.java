package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.Objects;

@Service
public class RestTemplateService {

    // http://localhost/api/server/hello
    // response
    public UserResponse hello(){
        //주소를 만드는 방법 : uricomponetbuilder
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","choi")     // 이렇게 queryParam 을 통해 값을 던져줄 수 있다.
                .queryParam("age",28)
                .encode()
                .build()
                .toUri();
        
        //RestTemplate을 이용해 통신
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate(); //pool 만들어써야됨

        //1. server 에서 String 값 받아주기.
        //1.1) getForObject
        //String result = restTemplate.getForObject(uri, String.class);
        //## ResponseEntity를 이용하여 status code와 body값을 가져올 수 있다.
        //1.2) getForEntity
//        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//        System.out.println(result.getStatusCode()); // status code
//        System.out.println(result.getBody()); // body 내용

        //json은 server 에서 어떻게 받는가?
        //## json 값 예시
        /*
        {
            "name" : "steve",
            "age" : 10
        }
         */

        //2.1) json to getForEntity
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        System.out.println(result.getStatusCode()); // status cod
        System.out.println(result.getBody()); // body 내용

        // get같은 경우, 주소만 호출하면 되는데, post일 경우 requestBody를 넣어서 보내야지.
        // uriComponentBuilder를 통해 uri만드는것

        return result.getBody();
    }


    public UserResponse post(){
        //http://localhost:9090/api/server/user/{userId}/name/{userName} : user를 등록시키는 것

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")            //userId userName
                .toUri();
        System.out.println(uri);

        //http body -> object -> object Mapper -> json -> rest template -> http body json

        UserRequest req = new UserRequest("steve", 10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri,req,UserResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }

    public UserResponse exchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")            //userId userName
                .toUri();
        System.out.println(uri);

        //http body -> object -> object Mapper -> json -> rest template -> http body json

        UserRequest req = new UserRequest("steve", 10);

        // ## Server가 Header를 요구할떄.
        // RequestEntity 만들기 -> header 를 실어서 보낸다. (요청을 보냄)
        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)                    //JSON 타입으로
                .header("x-authorization", "abcd")   //header에 담는거
                .header("custom-header","ffff")      //header값을 계속해서 추가할 수 있음.
                .body(req);//RequestBody에 넣는다.

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);    //requestEntity 넣기

        return response.getBody();
/*

        현업에서 json형태가 이런 경우가 있다.
        이 형식이 default형식으로 갖는 경우.

        ex) 이런걸 어떻게 디자인 하느냐?!
        {
            "header" : {
                "response_code" : "OK"
            },
            "body" : {
                "name" : "steve",
                "age"  : "10
            }
        }
 */
    }

    public Req<UserResponse> genericExchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")            //userId userName
                .toUri();
        System.out.println(uri);

        UserRequest userRequest = new UserRequest("steve", 10);
        Req <UserRequest> req = new Req<>();
        req.setHeader(
                new Req.Header()
        );
        req.setHttpBody(
                userRequest
        );

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)                    //JSON 타입으로
                .header("x-authorization", "abcd")   //header에 담는거
                .header("custom-header","ffff")      //header값을 계속해서 추가할 수 있음.
                .body(req);                                                 //RequestBody에 넣는다.

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){}); // generic 에 class 못함

        return response.getBody();    //getBody 두번,
    }
}
