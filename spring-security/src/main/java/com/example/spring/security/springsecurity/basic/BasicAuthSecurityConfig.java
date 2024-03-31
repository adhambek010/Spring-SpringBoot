package com.example.spring.security.springsecurity.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class BasicAuthSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
//            requests.anyRequest().authenticated();
            requests.requestMatchers("/users").hasRole("USER")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated();
        });
        //http.formLogin(Customizer.withDefaults());
        http.sessionManagement((
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ));
//        http.headers((header) -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:3000");
            }
        };
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        if (!jdbcUserDetailsManager.userExists("user")) {
//            UserDetails user = User.withUsername("user")
////                    .password("{noop}pwd")
//                    .password("pwd")
//                    .passwordEncoder(password -> passwordEncoderCustom().encode(password))
//                    .roles("USER")
//                    .build();
//            jdbcUserDetailsManager.createUser(user);
//        }
//        if (!jdbcUserDetailsManager.userExists("admin")) {
//            UserDetails admin = User.withUsername("admin")
////                    .password("{noop}pwd")
//                    .password("pwd")
//                    .passwordEncoder(password -> passwordEncoderCustom().encode(password))
//                    .roles("ADMIN")
//                    .build();
//            jdbcUserDetailsManager.createUser(admin);
//        }
//        return jdbcUserDetailsManager;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoderCustom(){
//        return new BCryptPasswordEncoder();
//    }
}
