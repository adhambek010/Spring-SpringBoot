package com.example.learnspringframework.examples.a5;

import com.example.learnspringframework.game.GameRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;


public class XmlConfig {
    public static void main(String[] args) {
        try (var conf = new ClassPathXmlApplicationContext("contextConfig.xml")) {
            Arrays.stream(conf.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(conf.getBean("name"));
            System.out.println(conf.getBean("age"));
            conf.getBean(GameRunner.class).run();
        }
    }
}
