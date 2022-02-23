package com.example.springcalculator.controller;

import com.example.springcalculator.component.Calculator;
import com.example.springcalculator.component.DollarCalculator;
import com.example.springcalculator.component.MarketApi;
import com.example.springcalculator.dto.Req;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculatorApiController.class)      //Web에 만 특화된 것만 하기 떄문에 자원을 줄인다.
@AutoConfigureWebMvc
@Import( {DollarCalculator.class, Calculator.class})
public class CalculatorApiControllerTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc;    //주입을 받아줘야함.

    @BeforeEach
    public void init(){
        Mockito.when(marketApi.connect()).thenReturn(3000); // Test 사전에 등록
    }

    //실제로 test 굳이 브라우저를 열지않고 테스트가 가능하다는점.
    @Test
    public void sumTest() throws Exception {
        // http://localhost:8080/api/sum?x=10&y=20

        //이런식으로 작성
        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                        .queryParam("x", "10")
                        .queryParam("y", "10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print());;    // <- 출력
    }

    //Post 테스트
    @Test
    public void minusTest() throws Exception {
        Req req = new Req();
        req.setX(10);
        req.setY(10);

        String json = new ObjectMapper().writeValueAsString(req); // json으로 변경 해줌

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
                .contentType(MediaType.APPLICATION_JSON)        //JSON 으로 변경해서 넘겨준다.
                .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.result").value("0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
        ).andDo(MockMvcResultHandlers.print());
    }
}

/*
 테스트커버리지
 옵션을 줘서 제외 시킬 수 도 있고, 빌드할떄 볼 수 있다.

 gradle 에 id jacoco 넣어주면된다. 
 gradle 에서 test 실행해주면 된다.

 */
