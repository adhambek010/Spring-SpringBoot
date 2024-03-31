package com.example.spring.learnspring.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//TODO: Configuration, AOP
@Configuration
@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before("execution(* com.example.spring.learnspring.aop.* .*.*(..))")
    public void logMethodCallBefore(JoinPoint joinPoint) {
        //TODO: Logic
        logger.info("Before Aspect - {} is called with following arguments: {}", joinPoint, joinPoint.getArgs());
    }

    @After("execution(* com.example.spring.learnspring.aop.* .*.*(..))")
    public void logMethodCallAfter(JoinPoint joinPoint) {
        logger.info("After Aspect - {} has executed", joinPoint);
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.spring.learnspring.aop.* .*.*(..))",
            throwing = "exception"
    )
    public void logMethodCallAfterException(JoinPoint joinPoint, Exception exception) {
        logger.info("AfterThrowing Aspect - {} has thrown an exception - {}", joinPoint, exception.toString());
    }

    @AfterReturning(
            pointcut = "execution(* com.example.spring.learnspring.aop.* .*.*(..))",
            returning = "resultValue"
    )
    public void logMethodCallAfterReturning(JoinPoint joinPoint, Object resultValue) {
        logger.info("AfterReturning Aspect - {} has returned a resultValue - {}", joinPoint, resultValue);
    }
}
