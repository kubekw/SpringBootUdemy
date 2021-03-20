package com.heroku.jakutest3.todoapp.model.projection;

import com.heroku.jakutest3.todoapp.model.Task;

import java.time.LocalDateTime;

public class GroupTaskWriteModel {

    private String description;
    private LocalDateTime deadLine;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public Task toTask(){
         return new Task(description,deadLine);

    }
}
