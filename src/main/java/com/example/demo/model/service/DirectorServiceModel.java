package com.example.demo.model.service;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;

public class DirectorServiceModel {
    private String name;
    private String careerInformation;

    public String getName() {
        return name;
    }

    public DirectorServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public DirectorServiceModel setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
