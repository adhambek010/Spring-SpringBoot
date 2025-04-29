package com.example.learnspringframework.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person (String name, int age, Address address){}

record Address(String city, String country){}

@Configuration
public class HelloSpringConfig {
    @Bean(name = "name")
    public String name(){
        return "Adkham";
    }
    @Bean(name = "age")
    public int age(){
        return 23;
    }
    @Bean(name = "Adham")
    public Person person1(){
        return new Person("Adham", 23, new Address("Samarkand", "Uzbekistan"));
    }
    @Bean
    public Person person2(){
        return new Person(name(), age(), address());
    }
    @Bean
    @Primary
    public Person person3(String name, int age, Address address2){
        return new Person(name, age, address2);
    }
    @Bean(name = "address2")
    @Qualifier
    public Address address(){
        return new Address("Lodz","Poland");
    }
}
