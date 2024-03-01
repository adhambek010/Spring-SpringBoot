package com.springboot.myfirstwebapplication.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private List<Todo> todos = new ArrayList<>(10);
    private int todoCount = 0;
    {
        todos.add(new Todo(++todoCount, "Adham","Master Spring Boot 3 & Spring Framework 6 with Java",
                LocalDate.now().plusMonths(1), false));
        todos.add(new Todo(++todoCount, "Adham", "A to Z DSA Algo",
                LocalDate.now().plusMonths(3), false));
        todos.add(new Todo(++todoCount, "Adham", "Learn AWS",
                LocalDate.now().plusMonths(6), false));
    }

    public List<Todo> findByUserName(String userName) {
        return todos;
    }

    public void addTodo(String userName, String description, LocalDate targetDate, boolean done){
        Todo todo = new Todo(++todoCount, userName, description, targetDate, done);
        todos.add(todo);
    }
}
