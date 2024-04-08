package com.example.security.springsecurityjwt;

import com.example.security.springsecurityjwt.auth.AuthenticationService;
import com.example.security.springsecurityjwt.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.security.springsecurityjwt.user.Role.ADMIN;
import static com.example.security.springsecurityjwt.user.Role.MANAGER;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service){
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Adkham")
					.lastname("Salaydinov")
					.email("example@gmail.com")
					.password("pwd")
					.role(ADMIN)
					.build();
			System.out.println("Admin token : "+service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Manager")
					.lastname("Admin")
					.email("manager@gmail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token : "+service.register(manager).getAccessToken());
		};
	}

}
