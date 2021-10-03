package com.company.ioc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlEncoder implements IEncoder {

    public String encode(String message){
        return URLEncoder.encode(message);
    }

}
