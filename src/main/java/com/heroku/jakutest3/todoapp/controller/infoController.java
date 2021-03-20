package com.heroku.jakutest3.todoapp.controller;

import com.heroku.jakutest3.todoapp.model.TaskConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class infoController {

    DataSourceProperties dataDource;
    TaskConfigurationProperties myProp;

    public infoController(DataSourceProperties dataDource, TaskConfigurationProperties myProp) {
        this.dataDource = dataDource;
        this.myProp = myProp;
    }

    @GetMapping("/url")
    public String infoURL(){
        return dataDource.getUrl();
    }

    @GetMapping("/prop")
    public boolean myProp(){
        return myProp.isAllowMultipleTasksFromTemplate();
    }
}
