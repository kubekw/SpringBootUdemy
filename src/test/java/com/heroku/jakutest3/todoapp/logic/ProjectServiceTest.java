package com.heroku.jakutest3.todoapp.logic;

import com.heroku.jakutest3.todoapp.model.TaskGroup;
import com.heroku.jakutest3.todoapp.model.TaskGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class ProjectServiceTest {

    @Test
    @DisplayName("should throw IllegalStateException when configured to allow just 1 group and the othr unDone group exists")
    void createGroup_noMultipleGroupsConfig_And_unDoneGroupExists_throwsOllegalStateException() {
        //given
        var mockGroupRepository = mock(TaskGroupRepository.class);

        //when
        //then

    }
}