package com.heroku.jakutest3.todoapp.controller;


import com.heroku.jakutest3.todoapp.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/showProjects")
public class ThProjectController {

private final ProjectRepository projectRepository;
private final TaskGroupRepository taskGroupRepository;

    public ThProjectController(ProjectRepository projectRepository, TaskGroupRepository taskGroupRepository) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
    }


    @PostMapping
    public String addProject(
        @ModelAttribute("project") @Valid Projects projects,
        BindingResult bindingResult,
        Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("projects",projectRepository.findAll());
            return "projects";
        }
        projectRepository.save(projects);
        model.addAttribute("project",projects);
        model.addAttribute("message","Dodano projekt");
        model.addAttribute("projects",projectRepository.findAll());
        return "projects";

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
public String update( Model model)
        throws Exception {
    Projects projects = new Projects();
    projects.setDescription("Wprowadz opis projektu");
    model.addAttribute("project", projects);
    model.addAttribute("projects", projectRepository.findAll());
    return "projects";
}

@ModelAttribute("ProjectsList")
List<Projects> getGroups(){
        return projectRepository.findAll();
}




}
