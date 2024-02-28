package com.example.learnspringframework.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConf {
    @Bean
    public GamingConsole game(){
        return new PacmanGame();
    }
    @Bean
    public GameRunner runner(GamingConsole console){
        return new GameRunner(game());
    }
}
