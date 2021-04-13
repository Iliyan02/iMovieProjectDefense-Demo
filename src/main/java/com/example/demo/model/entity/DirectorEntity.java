package com.example.demo.model.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "directors")
public class DirectorEntity extends BaseEntity {
    @Expose
    @Column(nullable = false)
    private String name;
    @Expose
    @Column(nullable = false, columnDefinition = "TEXT")
    private String careerInformation;

    public String getName() {
        return name;
    }

    public DirectorEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public DirectorEntity setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
