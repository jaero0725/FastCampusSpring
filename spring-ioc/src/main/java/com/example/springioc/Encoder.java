package com.example.springioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Encoder {

    private IEncoder iEncoder;

    public Encoder(@Qualifier("urlEncoder") IEncoder iEncoder){
        this.iEncoder = iEncoder;    //외부에서 여기에 주입해준다. (DI)
        //this.iEncoder = new Base64Encoder();
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }

    public void setiEncoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }
}
