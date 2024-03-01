package com.springboot.myfirstwebapplication.todo;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;

@Setter
@Getter
@ToString
public class Todo {
    private int id;
    private String userName;
    @Size(min = 12, message = "Enter atleast 10 characters")
    private String description;
    private LocalDate targetDate;
    private boolean done;

    public Todo(int id, String userName, String description, LocalDate targetDate, boolean done) {
        super();
        this.id = id;
        this.userName = userName;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }
}
