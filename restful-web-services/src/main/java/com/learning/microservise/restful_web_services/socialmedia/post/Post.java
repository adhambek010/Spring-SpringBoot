package com.learning.microservise.restful_web_services.socialmedia.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.microservise.restful_web_services.socialmedia.user.User;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    private Integer post_id;
    private String title;
    @Size(min = 2, max = 250)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
