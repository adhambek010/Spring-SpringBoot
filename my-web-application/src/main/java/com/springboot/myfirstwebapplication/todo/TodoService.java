package com.springboot.myfirstwebapplication.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private List<Todo> todos = new ArrayList<>(10);
    private int todoCount = 0;

    /*{
        todos.add(new Todo(++todoCount, "Adham", "Master Spring Boot 3 & Spring Framework 6 with Java 1",
                LocalDate.now().plusMonths(1), false));
        todos.add(new Todo(++todoCount, "Adham", "A to Z DSA Algo 1",
                LocalDate.now().plusMonths(3), false));
        todos.add(new Todo(++todoCount, "Adham", "Learn AWS 1",
                LocalDate.now().plusMonths(6), false));
    }*/

    public List<Todo> findByUserName(String userName) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(userName);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String userName, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todoCount, userName, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
