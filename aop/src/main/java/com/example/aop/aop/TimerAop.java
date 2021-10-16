package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//특정 메소드의 실행시간을 찍는 AOP
//bean  component 차이
//bean은 메서드에서 사용

@Aspect
@Component
public class TimerAop {

    @Pointcut("execution( * com.example.aop.controller..*.*(..))")
    public void cut(){ }

    @Pointcut("@annotation( com.example.aop.annotation.Timer)")
    private void enableTimer(){ }

    @Around("cut() && enableTimer()")   //2가지 조건을 다 쓰겠다는 뜻
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
        return result;
    }
}
