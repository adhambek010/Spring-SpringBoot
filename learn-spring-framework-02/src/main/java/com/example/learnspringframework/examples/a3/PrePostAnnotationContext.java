package com.example.learnspringframework.examples.a3;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class SomeClass {
    private SomeDependency dependency;

    public SomeClass(SomeDependency dependency) {
        this.dependency = dependency;
        System.out.println("All Dependencies are ready!");
    }

    @PostConstruct
    public void initialize() {
        dependency.getReady();
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("Cleaned up!");
    }
}

@Component
class SomeDependency {
    public void getReady() {
        System.out.println("Dependency is ready!");
    }
}

@Configuration
@ComponentScan
public class PrePostAnnotationContext {
    public static void main(String[] args) {
        try (var conf = new AnnotationConfigApplicationContext(PrePostAnnotationContext.class)) {

        }
    }
}
