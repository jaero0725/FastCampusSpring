package com.company.ioc;

public class Main {
    public static void main(String[] args) {
        String url = "www.naver.com/books/it?page=10&size-20&name=spring-boot";

        // Base 64 encoding
        IEncoder base64Encoder = new Base64Encoder();
        String result = base64Encoder.encode(url);
        System.out.println(result);

        // url encoding
        IEncoder urlEncoder = new UrlEncoder();
        String urlResult = urlEncoder.encode(url);
        System.out.println(urlResult);

        //개발자가 주입 시켜준다. -> 코드의 관리 재사용성이 처리가 편해졌다.
        Encoder encoder = new Encoder(new Base64Encoder()); // 여기 주입 객체만 바꾸면 된다.
        String rec = encoder.encode(url);
        System.out.println(rec);

    }
}


