package com.example.demo.model.binding;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class DirectorAddBindingModel {
    @Size(min = 3, max = 40, message = "Director's name should be between 3 and 40 characters.")
    private String name;
    @Size(min = 20, max = 1000, message = "Director's career information should be between 20 and 300 characters.")
    private String careerInformation;

    public String getName() {
        return name;
    }

    public DirectorAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public DirectorAddBindingModel setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
