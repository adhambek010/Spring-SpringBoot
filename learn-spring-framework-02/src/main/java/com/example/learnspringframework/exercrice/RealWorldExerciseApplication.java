package com.example.learnspringframework.exercrice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RealWorldExerciseApplication {
    public static void main(String[] args) {
        try (var conf = new AnnotationConfigApplicationContext(BusinessCalculationService.class)) {
            System.out.println(conf.getBean(BusinessCalculationService.class).findMax());
        }
    }
}
