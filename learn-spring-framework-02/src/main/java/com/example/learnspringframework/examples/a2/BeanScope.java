package com.example.learnspringframework.examples.a2;

import lombok.ToString;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
class NormalClass{

}
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass{

}

@Configuration
@ComponentScan
public class BeanScope {
    public static void main(String[] args) {
        try(var conf = new AnnotationConfigApplicationContext(BeanScope.class)){
            System.out.println(conf.getBean(NormalClass.class));
            System.out.println(conf.getBean(NormalClass.class) + "\n");

            System.out.println(conf.getBean(PrototypeClass.class));
            System.out.println(conf.getBean(PrototypeClass.class));
            System.out.println(conf.getBean(PrototypeClass.class));
        }
    }
}
