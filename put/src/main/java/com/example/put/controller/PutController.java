package com.example.put.controller;

import com.example.put.dto.PutRequestDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PutController {

    @PutMapping("/put")
    public PutRequestDto put(@RequestBody PutRequestDto putRequestDto){
        System.out.println(putRequestDto.toString());
        return putRequestDto;
    }

    // => CarList 가 안들어옴  : Snake case, Camel case 차이
    /*
    {
        "name" : "steve",
            "age" : 20,
            "car_list" : [
        {
            "name" : "BMW",
                "car_number" : "11가 1234"
        },
        {
            "name" : "AUDI",
                "car_number" : "23누 1243"
        }
	]
    }
     */
}
