package com.heroku.jakutest3.todoapp.controller;


import com.heroku.jakutest3.todoapp.model.*;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/showTasks")
public class ThController {

    private final TaskRepository taskRepository;
    private final TaskGroupRepository taskGroupRepository;

    public ThController(TaskRepository taskRepository, ProjectRepository projectRepository, TaskGroupRepository taskGroupRepository) {
        this.taskRepository = taskRepository;

        this.taskGroupRepository = taskGroupRepository;
    }


@PostMapping
    public String dodajemyDane(
        @ModelAttribute("task") @Valid Task task,
        BindingResult bindingResult,
        Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("taski",taskRepository.findAll());
            return "tasks";
        }
        taskRepository.save(task);
        model.addAttribute("task",task);
        model.addAttribute("message","Dodano taska");
        model.addAttribute("taski",taskRepository.findAll());
        return "tasks";

    }

//    @PostMapping
//    public String dodajemyDane(
//            @ModelAttribute("task") @Valid String description,
//            BindingResult bindingResult,
//            Model model) {
//        if(bindingResult.hasErrors()){
//            return "tasks";
//        }
//        Task task = new Task(description);
//        taskRepository.save(task);
//        model.addAttribute("task",task);
//        model.addAttribute("message","Dodano taska");
//        model.addAttribute("taski",taskRepository.findAll());
//        return "tasks";
//
//    }

@GetMapping
@RequestMapping(value = "/group/{id}")
public String taskByGroupId(@PathVariable int id, Model model)
        throws Exception {
    Task task = new Task();
    task.setDescription("Wprowadz opis");
    model.addAttribute("task", task);
    model.addAttribute("taski", taskRepository.findAllByGroupId(id));

    //FIXME stworzyć nowy widok dla tasków z grupy projekty oraz toogla
    return "tasks";
}

    @GetMapping
    public String update(Model model)
            throws Exception {
        Task task = new Task();
        task.setDescription("Wprowadz opis");
        model.addAttribute("task", task);
        model.addAttribute("taski", taskRepository.findAll());
        return "tasks";
    }


    @Transactional
    @PostMapping(value = "/{id}", params = "toogleTask")
    public String toggleTask(@PathVariable int id, Model model) {
        Task task = new Task();
        if(!taskRepository.existsById(id)) {
            model.addAttribute("task", task);
            model.addAttribute("taski", taskRepository.findAll());
            return "tasks";
        }
        taskRepository.findById(id)
                .ifPresent(taskToToogle -> taskToToogle.setDone(!taskToToogle.isDone()));
        model.addAttribute("task", task);
        model.addAttribute("taski", taskRepository.findAll());
        return "tasks";
    }

@ModelAttribute("taskGroup")
    List<TaskGroup> getGroups(){
        return taskGroupRepository.findAll();
}




}
