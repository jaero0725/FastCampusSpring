package com.example.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Req<T> {

    private Header  header;

    private T body;    //내용이 바뀐다. => 제네릭타입

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Header{
        private String responseCode;
    }

}
