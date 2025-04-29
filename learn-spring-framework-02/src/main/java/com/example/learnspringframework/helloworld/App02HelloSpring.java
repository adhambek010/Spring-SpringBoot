package com.example.learnspringframework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App02HelloSpring {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(HelloSpringConfig.class)) {
            /*System.out.println(context.getBean("name"));
            System.out.println(context.getBean("age"));
            System.out.println(context.getBean("Adham"));
            System.out.println(context.getBean("person2"));
            System.out.println(context.getBean("person3"));
            System.out.println(context.getBean("address2"));
            System.out.println(context.getBean(Address.class));*/
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        }
    }
}
