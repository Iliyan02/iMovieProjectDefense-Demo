package com.example.demo.service.impl;

import com.example.demo.model.entity.DirectorEntity;
import com.example.demo.model.service.DirectorServiceModel;
import com.example.demo.repository.DirectorsRepository;
import com.example.demo.service.DirectorService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {
    private final Gson gson;
    private final DirectorsRepository directorsRepository;
    private final Resource directorsFile;
    private final ModelMapper modelMapper;


    public DirectorServiceImpl(Gson gson, DirectorsRepository directorsRepository, @Value("classpath:init/directors.json") Resource directorsFile, ModelMapper modelMapper) {
        this.gson = gson;
        this.directorsRepository = directorsRepository;
        this.directorsFile = directorsFile;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedDirectors() {
        if (directorsRepository.count() == 0){
            try {
                DirectorEntity[] directorEntities =
                        gson.fromJson(Files.readString(Path.of(directorsFile.getURI())), DirectorEntity[].class);

                Arrays.stream(directorEntities)
                        .forEach(directorsRepository::save);

            } catch (Exception e){
                throw new IllegalStateException("Cannot seed directors");
            }
        }
    }

    @Override
    public List<String> findAllDirectors() {
        return directorsRepository.findAllDirectors();
    }

    @Override
    public DirectorEntity findByName(String director) {
        return directorsRepository.findByName(director)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void addDirector(DirectorServiceModel directorServiceModel) {
        directorsRepository.save(modelMapper.map(directorServiceModel, DirectorEntity.class));
    }

    @Override
    public boolean containsDirector(String name) {
        return directorsRepository.findDirectorEntityByName(name).isPresent();
    }

    @Override
    public List<DirectorEntity> findAll() {
        return directorsRepository.findAll();
    }

    @Override
    public void deleteDirector(Long id) {
        directorsRepository.deleteById(id);
    }
}
