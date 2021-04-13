package com.example.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table( name = "logs")
public class LogEntity extends BaseEntity {
    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private MovieEntity movieEntityEntity;
    @Column(name = "action", nullable = false)
    private String action;
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public LogEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public MovieEntity getMovieEntityEntity() {
        return movieEntityEntity;
    }

    public LogEntity setMovieEntityEntity(MovieEntity movieEntityEntity) {
        this.movieEntityEntity = movieEntityEntity;
        return this;
    }

    public String getAction() {
        return action;
    }

    public LogEntity setAction(String action) {
        this.action = action;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
