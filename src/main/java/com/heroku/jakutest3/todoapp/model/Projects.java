package com.heroku.jakutest3.todoapp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
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

    Set<TaskGroup> getGroups() {
        return groups;
    }

     void setGroups(Set<TaskGroup> groups) {
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
