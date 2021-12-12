package com.example.client.controller;

import com.example.client.dto.Req;
import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    //요즘에 Autowired말고 이렇게 한다. alt enter 명시적 주입
    private final RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public Req<UserResponse> getHello(){
        //return restTemplateService.hello();
        //return restTemplateService.post();
        //return restTemplateService.exchange();
        return restTemplateService.genericExchange();
    }
}
