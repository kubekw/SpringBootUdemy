package com.heroku.jakutest3.todoapp.adapter;



import com.heroku.jakutest3.todoapp.model.Task;
import com.heroku.jakutest3.todoapp.model.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository <Task, Integer>{

    @Override
boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);

    @Override
    List<Task> findAllByGroupId(Integer groupId);

}
