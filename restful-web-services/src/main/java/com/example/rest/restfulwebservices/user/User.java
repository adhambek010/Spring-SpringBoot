package com.example.rest.restfulwebservices.user;

import com.example.rest.restfulwebservices.user.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Size(min = 2, max = 50, message = "Name should have at least 2 characters")
    private String name;
    @Past(message = "Birthdate should be in past")
    private LocalDate birthDate;
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts;
    public Post setPost(Post post) {
        this.posts.add(post);
        return post;
    }
}