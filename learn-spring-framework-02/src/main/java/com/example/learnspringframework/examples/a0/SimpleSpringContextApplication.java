package com.example.learnspringframework.examples.a0;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class SimpleSpringContextApplication {
    public static void main(String[] args) {
        try(var conf = new AnnotationConfigApplicationContext(SimpleSpringContextApplication.class)){
            Arrays.stream(conf.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
