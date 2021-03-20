package com.heroku.jakutest3.todoapp.model;

import javax.persistence.*;

@Entity
public class ProjectSteps {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private long daysToDeadLine;
    @ManyToOne()
    @JoinColumn(name = "projects_id")
    private Projects projects;

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

    public long getDaysToDeadLine() {
        return daysToDeadLine;
    }

    public void setDaysToDeadLine(long daysToDeadLine) {
        this.daysToDeadLine = daysToDeadLine;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }
}
