package com.example.spring.learnspring.aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {
    @Pointcut("execution(* com.example.spring.learnspring.aop.* .*.*(..))")
    public void businessAndDataPackageConfig(){}

    @Pointcut("execution(* com.example.spring.learnspring.aop.business.*.*(..))")
    public void businessPackageConfig(){}

    @Pointcut("execution(* com.example.spring.learnspring.aop.data.*.*(..))")
    public void dataPackageConfig(){}

    @Pointcut("bean(*Service*)")
    public void allPackageConfigWithBean(){}

    @Pointcut("@annotation(com.example.spring.learnspring.aop.annotations.TrackTime)")
    public void trackTimeAnnotation(){}
}
