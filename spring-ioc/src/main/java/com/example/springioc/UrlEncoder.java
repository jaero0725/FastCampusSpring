package com.example.springioc;

import org.springframework.stereotype.Component;

import java.net.URLEncoder;

@Component
public class UrlEncoder implements IEncoder {

    public String encode(String message){
        return URLEncoder.encode(message);
    }

}
