package com.example.learnspringframework.examples.a1;

import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@ToString
@Setter
class YourBusinessClass{
    //@Autowired
    private Dependency1 dependency1;
    //@Autowired
    private Dependency2 dependency2;

    //@Autowired
    public YourBusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
        System.out.println("Constructor dependency");
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }

    /*@Autowired
    public void setDependency1(Dependency1 dependency1) {
        System.out.println("Setter Injection - set Dependency 1");
        this.dependency1 = dependency1;
    }
    @Autowired
    public void setDependency2(Dependency2 dependency2) {
        System.out.println("Setter Injection - set Dependency 2");
        this.dependency2 = dependency2;
    }*/
}
@Component
@ToString
class Dependency1{

}
@Component
@ToString
class Dependency2{

}

@Configuration
@ComponentScan
public class DependencyInjectionApplication {
    public static void main(String[] args) {
        try(var conf = new AnnotationConfigApplicationContext(DependencyInjectionApplication.class)){
            Arrays.stream(conf.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println( conf.getBean(YourBusinessClass.class));
        }
    }
}
