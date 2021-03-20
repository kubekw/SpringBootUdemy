package com.heroku.jakutest3.todoapp.controller;

import com.heroku.jakutest3.todoapp.logic.TaskGroupService;
import com.heroku.jakutest3.todoapp.logic.TaskService;
import com.heroku.jakutest3.todoapp.model.Task;
import com.heroku.jakutest3.todoapp.model.TaskGroup;
import com.heroku.jakutest3.todoapp.model.TaskGroupRepository;
import com.heroku.jakutest3.todoapp.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/group")
public class TaskGroupController {

    private static final Logger logger = LoggerFactory.getLogger(TaskGroupController.class);

    private final TaskGroupRepository repository;
    private final TaskGroupService taskGroupService;

    public TaskGroupController(TaskGroupRepository repository, TaskGroupService taskGroupService) {
        this.repository = repository;
        this.taskGroupService = taskGroupService;
    }

    @GetMapping
    ResponseEntity<List<TaskGroup>> readAllGroups() {
        logger.warn("Exposing all the groups!");

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskGroup> getGroup(@PathVariable int id){

        logger.warn("Exposing group number "+id);

        return  repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<TaskGroup> postTask(@RequestBody @Valid TaskGroup toPost){
        TaskGroup result = repository.save(toPost);
        return  ResponseEntity.created(URI.create("/tasks/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateGroup(@PathVariable int id, @RequestBody @Valid TaskGroup toUpdate) {
        if(!repository.existsById(id)) {
            logger.warn("Put fail, group number "+id+" not exist");
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(taskGroup -> {
                    taskGroup.updateFrom(toUpdate);
                    repository.save(taskGroup);
                });
        logger.warn("Updated group number "+id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> toogleGroup(@PathVariable int id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(taskGroup -> taskGroup.setDone(!taskGroup.isDone()));
        return ResponseEntity.noContent().build();
    }

    /*
    @GetMapping("/search/done")
    ResponseEntity <List<Task>> readDoneTasks(@RequestParam(defaultValue = "true") boolean state) {
        return ResponseEntity.ok(
                repository.findByDone(state)
        );
    }





*/


}
