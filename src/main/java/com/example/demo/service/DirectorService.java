package com.example.demo.service;

import com.example.demo.model.entity.DirectorEntity;
import com.example.demo.model.entity.MovieEntity;
import com.example.demo.model.service.DirectorServiceModel;

import java.util.List;

public interface DirectorService {
    void seedDirectors();

    List<String> findAllDirectors();

    DirectorEntity findByName(String director);

    void addDirector(DirectorServiceModel directorServiceModel);

    boolean containsDirector(String name);

    List<DirectorEntity> findAll();

    void deleteDirector(Long id);
}
