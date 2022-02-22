package com.example.springcalculator.component;

import com.example.springcalculator.dto.Req;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest
 // SpringBootTest를 붙이면 모두 import되므로 안해두됨 밑에부분
 // @Import({MarketApi.class, DollarCalculator.class})//marketApi 주입받으므로 필요 -> @Import를 통해 주입
public class DollarCalculatorTest {


    @MockBean
    private MarketApi marketApi;        //모킹 처리

    @Autowired
    private Calculator calculator;

    @Test
    public void dollarCalculatorTest(){

        Mockito.when(marketApi.connect()).thenReturn(3000);     //이부분

        int sum = calculator.sum(10,10);
        int minus = calculator.minus(10,10);

        Assertions.assertEquals(60000, sum); //이부분
        Assertions.assertEquals(0,minus);

    }



}
