package com.example.learnspringframework.lazyiinitialization;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class Component1{

}
@Component
@Lazy
class Component2{
    private Component1 component;

    public Component2(Component1 component){
        System.out.println("Component-1 Initialization Logic");
        this.component = component;
    }

    public void initialize(){
        System.out.println("Component-2 Initialized");
    }
}

@Configuration
@ComponentScan
public class LazyMain {
    public static void main(String[] args) {
        try (var conf = new AnnotationConfigApplicationContext(LazyMain.class)){
            System.out.println("Initialization of context is completed");
            conf.getBean(Component2.class).initialize();
        }
    }
}
