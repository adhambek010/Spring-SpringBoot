package com.learning.microservise.restful_web_services.socialmedia.user;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class User {
    private Integer id;
    @Size(min = 2, max = 20)
    private String name;
    @Past
    private LocalDate birthday;
}
