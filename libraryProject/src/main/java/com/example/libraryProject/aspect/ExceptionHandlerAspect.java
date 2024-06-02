package com.example.libraryProject.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlerAspect {

    @AfterThrowing(pointcut = "execution(* com.example.libraryProject.service.*.*(..))", throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("ExceptionHandlingAspect: Exception thrown - " + exception.getMessage());
    }
}
