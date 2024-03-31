package com.example.restfulapi.todo;

import com.example.restfulapi.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/users")
public class TodoResource {

    private TodoService service;

    public TodoResource(TodoService service) {
        this.service = service;
    }

    @GetMapping("/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
        return service.findByUsername(username);
    }

    @GetMapping("/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable int id){
        return service.findById(id);
    }

    @DeleteMapping("/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable int id,
                                           @RequestBody Todo todo){
        service.updateTodo(todo);
        return todo;
    }

    @PostMapping("/{username}/todo")

    public Todo createTodo(@PathVariable String username,
                           @RequestBody Todo todo) {

        return service.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
    }
}
