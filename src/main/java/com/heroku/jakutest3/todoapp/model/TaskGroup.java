package com.heroku.jakutest3.todoapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "task_groups")
public class TaskGroup {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  int id;
    @NotBlank(message = "Task groups description must not be empty")
    private String description;
    private boolean done;
    @Embedded
    private Audit audit = new Audit();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Set<Task> tasks;
    @ManyToOne
    @JoinColumn(name = "projects_id")
    private Projects projects;



    public TaskGroup() {
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

     public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "TaskGroup{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", audit=" + audit +
                ", tasks=" + tasks +
                ", projects=" + projects +
                '}';
    }

    public void updateFrom(final TaskGroup source){
        description= source.description;
        done = source.done;
        tasks = source.tasks;
        projects = source.projects;

    }
}

