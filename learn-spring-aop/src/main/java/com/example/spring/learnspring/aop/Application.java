package com.example.spring.learnspring.aop;

import com.example.spring.learnspring.aop.business.BusinessService1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private final BusinessService1 service1;
	private Logger logger = LoggerFactory.getLogger(getClass());

	public Application(BusinessService1 service1) {
		this.service1 = service1;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Value returned is {}", service1.calculateMax());
	}
}
