package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution( * com.example.aop.controller..*.*(..))")
    public void cut(){ }

    @Pointcut("@annotation( com.example.aop.annotation.Timer)")
    private void enableDecode(){ }

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        for(Object arg : args){
            if(arg instanceof User){
                User user = (User) arg;
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email), StandardCharsets.UTF_8);
                user.setEmail(email);
            }
        }

    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        if(returnObj instanceof User){
            User user = (User) returnObj ;
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);
        }
    }

}