package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("hello");

        // Text JSON -> Object
        // Object -> Text JSON

        //Controller req json(text) -> object
        // response object -> json(text)


        //1. Object -> text
        var objectMapper = new ObjectMapper();
        var user = new User("Steve",10, "010-2234-3232");
        var text = objectMapper.writeValueAsString(user); //objectMapper 는 Getmethod 참조
        //Get Method를 참조한다.
        System.out.println(text);

        //2. text -> Object
        // object mapper는 Default 생성자가 있어야됨.
        var objectUser = objectMapper.readValue(text,User.class);
        System.out.println(objectUser);

    }

}
