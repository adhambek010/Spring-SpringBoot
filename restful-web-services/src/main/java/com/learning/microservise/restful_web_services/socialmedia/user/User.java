package com.learning.microservise.restful_web_services.socialmedia.user;

import com.learning.microservise.restful_web_services.socialmedia.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_details")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2)
    private String name;
    @Past
    private LocalDate birthdate;
    @OneToMany
    private List<Post> posts;

    public User(int i, String adam, LocalDate localDate) {
    }
}
