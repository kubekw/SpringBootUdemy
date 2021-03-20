package com.heroku.jakutest3.todoapp.model;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
public class Audit {

    private LocalDateTime createdOn;
    private LocalDateTime updatedON;

    @PrePersist
    void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void premarge() {
        updatedON = LocalDateTime.now();
    }
}
