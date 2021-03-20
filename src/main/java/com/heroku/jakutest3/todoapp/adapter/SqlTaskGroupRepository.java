package com.heroku.jakutest3.todoapp.adapter;

import com.heroku.jakutest3.todoapp.model.TaskGroup;
import com.heroku.jakutest3.todoapp.model.TaskGroupRepository;
import org.hibernate.loader.custom.sql.SQLCustomQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroup, Integer> {

    //FIXME NIE DZIALA SQL
//    @Override
//    @Query("select distinct g from TaskGroup g join fetch g.tasks")
//    List<TaskGroup> findAll();

    @Override
    boolean existsByDoneIsFalseAndProjects_id(Integer projectsid);


}
