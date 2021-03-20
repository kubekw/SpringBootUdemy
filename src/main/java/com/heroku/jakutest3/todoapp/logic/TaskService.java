package com.heroku.jakutest3.todoapp.logic;

import com.heroku.jakutest3.todoapp.model.Task;
import com.heroku.jakutest3.todoapp.model.TaskRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;

    }

    // ASYNCHRONICZNOŚć
    @Async
    public CompletableFuture<List<Task>> findAllAsync(){
        return CompletableFuture.supplyAsync(repository::findAll);
    }


}
