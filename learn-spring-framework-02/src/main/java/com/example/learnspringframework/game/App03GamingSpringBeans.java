package com.example.learnspringframework.game;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.learnspringframework.game")
public class App03GamingSpringBeans {
    public static void main(String[] args) {
        try(var conf = new AnnotationConfigApplicationContext(App03GamingSpringBeans.class)){
            conf.getBean(GamingConsole.class).up();
            conf.getBean(GameRunner.class).run();
        }
    }
}
