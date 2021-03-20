package com.heroku.jakutest3.todoapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Opis projektu nie może być pusty.")
    private String description;
    @OneToMany( mappedBy = "projects")
    private Set<TaskGroup> groups;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projects")
    private Set<ProjectSteps> steps;


    public Set<ProjectSteps> getSteps() {
        return steps;
    }

    public void setSteps(Set<ProjectSteps> steps) {
        this.steps = steps;
    }

    public Set<TaskGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<TaskGroup> groups) {
        this.groups = groups;
    }


    public int getId() {
        return id;
    }

     void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

     public void setDescription(String description) {
        this.description = description;
    }
}
