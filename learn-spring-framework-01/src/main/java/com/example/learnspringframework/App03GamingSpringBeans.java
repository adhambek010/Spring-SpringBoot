package com.example.learnspringframework;

import com.example.learnspringframework.game.GameConf;
import com.example.learnspringframework.game.GameRunner;
import com.example.learnspringframework.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App03GamingSpringBeans {
    public static void main(String[] args) {
        try(var config = new AnnotationConfigApplicationContext(GameConf.class)) {
            config.getBean(GamingConsole.class).up();
            config.getBean(GameRunner.class).run();
        }
    }
}
