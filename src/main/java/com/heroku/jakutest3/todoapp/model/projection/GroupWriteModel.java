package com.heroku.jakutest3.todoapp.model.projection;

import com.heroku.jakutest3.todoapp.model.TaskGroup;
import org.aspectj.weaver.ast.Var;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupWriteModel {

    private String description;
    private Set<GroupTaskWriteModel> tasks;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GroupTaskWriteModel> getTasks() {
        return tasks;
    }

    public void setTasks(Set<GroupTaskWriteModel> tasks) {
        this.tasks = tasks;
    }

    //TODO TaskGroup zamiast VAR
    public TaskGroup toGroup(){
        TaskGroup result =  new TaskGroup();
        result.setDescription(description);

        result.setTasks(
                tasks.stream()
                        .map(GroupTaskWriteModel::toTask)
                        .collect(Collectors.toSet())
        );

        return result;

    }
}
