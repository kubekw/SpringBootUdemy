package com.heroku.jakutest3.todoapp.logic;

import com.heroku.jakutest3.todoapp.model.*;
import com.heroku.jakutest3.todoapp.model.projection.GroupReadModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private TaskGroupRepository taskGroupRepository;
    private TaskConfigurationProperties taskConfigurationProperties;

    public ProjectService(ProjectRepository projectRepository,
                          TaskGroupRepository taskGroupRepository,
                          TaskConfigurationProperties taskConfigurationProperties) {
        this.projectRepository = projectRepository;
        this.taskGroupRepository = taskGroupRepository;
        this.taskConfigurationProperties = taskConfigurationProperties;
    }

    public List<Projects> readAllProjects() {
        return projectRepository.findAll();
    }

    public Projects save(final Projects source) {
        return projectRepository.save(source);

    }

    public GroupReadModel createGroup(LocalDateTime deadLine, int projectId) {
        if (!taskConfigurationProperties.isAllowMultipleTasksFromTemplate()
                && taskGroupRepository.existsByDoneIsFalseAndProjects_id(projectId)) {
            throw new IllegalStateException("Only one undone group from project is allowed");
        }
        TaskGroup result = projectRepository.findById(projectId)
                .map(projects -> {
                    TaskGroup taskGroup = new TaskGroup();
                    taskGroup.setDescription(projects.getDescription());
                    taskGroup.setTasks(
                            projects.getSteps().stream()
                                    .map(projectSteps -> new Task(
                                            projectSteps.getDescription(),
                                            deadLine.plusDays(projectSteps.getDaysToDeadLine()))
                                    ).collect(Collectors.toSet())

                    );
                    return taskGroup;
                }).orElseThrow(() -> new IllegalArgumentException("Project with given ID not found"));
        return new GroupReadModel(result);

    }
}
