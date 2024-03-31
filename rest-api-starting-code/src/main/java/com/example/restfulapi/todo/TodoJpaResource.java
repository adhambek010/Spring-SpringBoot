package com.example.restfulapi.todo;

import com.example.restfulapi.todo.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class TodoJpaResource {

    private final TodoRepository repository;

    public TodoJpaResource(TodoService service, TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
        return repository.findByUsername(username);
    }

    @GetMapping("/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable Integer id){
        return repository.findById(id).get();
    }

    @DeleteMapping("/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable Integer id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable Integer id,
                                           @RequestBody Todo todo){
        System.out.println("=======================Request came to update=======================");
        repository.save(todo);
        System.out.println("=======================Saved Successfully=======================");
        return todo;
    }

    @PostMapping("/{username}/todo")

    public Todo createTodo(@PathVariable String username,
                           @RequestBody Todo todo) {
        todo.setUsername(username);
        todo.setId(null);
        repository.save(todo);

        return repository.save(todo);
    }
}
