package com.example.spring.learnspring.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceTracingAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Around("com.example.spring.learnspring.aop.aspects.CommonPointcutConfig.businessAndDataPackageConfig()")
    @Around("com.example.spring.learnspring.aop.aspects.CommonPointcutConfig.trackTimeAnnotation()")
    public Object findExecutionTime(ProceedingJoinPoint joinPoint){
        //TODO: Start a timer. Execute the method. Stop the timer

        long startTimeMillis = System.currentTimeMillis();

        Object returnValue = null;
        try {
            returnValue = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        long stopTimeMillis = System.currentTimeMillis();

        long executionDuration = stopTimeMillis - startTimeMillis;

        logger.info("Around Aspect - {} Method executed in {} ms", joinPoint, executionDuration);

        return returnValue;
    }
}
