package com.heroku.jakutest3.todoapp.logic;

import com.heroku.jakutest3.todoapp.model.TaskGroup;
import com.heroku.jakutest3.todoapp.model.TaskGroupRepository;
import com.heroku.jakutest3.todoapp.model.TaskRepository;
import com.heroku.jakutest3.todoapp.model.projection.GroupReadModel;
import com.heroku.jakutest3.todoapp.model.projection.GroupWriteModel;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskGroupService {

    private TaskGroupRepository repository;
    private TaskRepository taskRepository;

    public TaskGroupService(TaskGroupRepository repository) {
        this.repository = repository;
    }

    public GroupReadModel createGroup(GroupWriteModel source){
        TaskGroup result = repository.save(source.toGroup());
        return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll(){
        return repository.findAll()
                .stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toogleGroup(int groupId){
        if (taskRepository.existsByDoneIsFalseAndGroup_Id(groupId)){
            throw new IllegalStateException("Group has undone tasks. Done all the task first");
        }
        TaskGroup result = repository.findById(groupId)
                .orElseThrow(()->new IllegalArgumentException("TaskGroup with given id not found"));
        result.setDone((result.isDone()));

    }
}
