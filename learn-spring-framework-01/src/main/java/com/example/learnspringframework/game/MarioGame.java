package com.example.learnspringframework.game;

import lombok.ToString;

@ToString
public class MarioGame implements GamingConsole{
    @Override
    public void up(){
        System.out.println("Jump");
    }
    @Override
    public void down(){
        System.out.println("Go into hole");
    }
    @Override
    public void left(){
        System.out.println("Go back");
    }
    @Override
    public void right(){
        System.out.println("Accelerate");
    }
}
