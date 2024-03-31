package com.rest.webservices.restfulwebservices.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity(name = "user_details")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 3, message = "Name should have at least 2 characters")
    private String name;
    @Past(message = "Birth Date should be in the past")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    public User(int i, String adam, LocalDate localDate) {
    }
}
