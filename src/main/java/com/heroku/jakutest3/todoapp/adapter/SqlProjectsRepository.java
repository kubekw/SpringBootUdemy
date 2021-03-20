package com.heroku.jakutest3.todoapp.adapter;

import com.heroku.jakutest3.todoapp.model.ProjectRepository;
import com.heroku.jakutest3.todoapp.model.Projects;
import com.heroku.jakutest3.todoapp.model.TaskGroup;
import com.heroku.jakutest3.todoapp.model.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SqlProjectsRepository extends ProjectRepository, JpaRepository<Projects, Integer> {

//    @Override
//    @Query("select distinct p from Projects p join fetch p.steps")
//    List<Projects> findAll();


}
