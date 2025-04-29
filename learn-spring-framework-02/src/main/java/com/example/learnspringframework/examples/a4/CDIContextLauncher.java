package com.example.learnspringframework.examples.a4;


import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Component
@Named
@ToString
class BusinessService {
    private DataService service;

    public DataService getService() {
        return service;
    }

    //@Autowired
    @Inject
    public void setService(DataService service) {
        System.out.println("Setter Injection");
        this.service = service;
    }
}

//@Component
@Named
@ToString
class DataService {

}

@Configuration
@ComponentScan
public class CDIContextLauncher {
    public static void main(String[] args) {
        try (var conf = new AnnotationConfigApplicationContext(CDIContextLauncher.class)) {
            Arrays.stream(conf.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(conf.getBean(BusinessService.class).getService());
        }
    }
}