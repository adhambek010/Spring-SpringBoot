package com.example.rest.restfulwebservices.user.post;

import com.example.rest.restfulwebservices.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Size(min = 2, max = 50)
    private String title;
    @Size(min = 10)
    private String content;

    @JsonIgnore
    @ManyToOne()
    private User user;
}
