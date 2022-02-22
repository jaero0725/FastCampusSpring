package com.example.springcalculator.controller;

import com.example.springcalculator.component.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalculatorApiController {

    private final Calculator calculator;

    /*
      서버를 실행하지 않고, TEST로 확인
     */
    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y){
        //dollarCalculator.init();
        return calculator.sum(x,y);
    }

    @GetMapping("/minus")
    public int minus(@RequestParam int x, @RequestParam int y){
        return calculator.minus(x,y);
    }
}
