package com.company.ioc;

import java.util.Base64;

public class Encoder {

    private IEncoder iEncoder;

    public Encoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;    //외부에서 여기에 주입해준다. (DI)
        //this.iEncoder = new Base64Encoder();
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }

}
