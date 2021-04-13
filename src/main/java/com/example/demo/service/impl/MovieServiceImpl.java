package com.example.demo.service.impl;

import com.example.demo.model.entity.DirectorEntity;
import com.example.demo.model.entity.Genre;
import com.example.demo.model.entity.MovieEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.service.MovieServiceModel;
import com.example.demo.model.view.MovieViewModel;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DirectorService;
import com.example.demo.service.MovieService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final DirectorService directorService;
    private final Gson gson;
    private final Resource moviesFile;


    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper, UserRepository userRepository, DirectorService directorService, Gson gson, @Value("classpath:init/movies.json") Resource moviesFile) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.directorService = directorService;
        this.gson = gson;

        this.moviesFile = moviesFile;
    }

    @Override
    public void createMovie(MovieServiceModel movieServiceModel) {
        MovieEntity movieEntity = modelMapper.map(movieServiceModel, MovieEntity.class);
        UserEntity creator = userRepository.findByUsername(movieServiceModel.getUser())
                .orElseThrow(() -> new IllegalArgumentException("Creator" + movieServiceModel.getUser() + "could not be found"));

        movieEntity.setUserEntity(creator);

        DirectorEntity directorEntity = directorService.findByName(movieServiceModel.getDirector());
//        movieEntity.setReleaseDate(movieServiceModel.getReleaseDate());

        movieEntity.setDirectorEntity(directorEntity);
        movieRepository.save(movieEntity);
    }

    @Override
    public MovieViewModel findById(Long id) {
        return movieRepository
                .findById(id)
                .map(movieEntity -> {
                    MovieViewModel movieViewModel  = modelMapper
                            .map(movieEntity, MovieViewModel.class);
                    movieViewModel.setDirector(movieEntity.getDirectorEntity().getName());
                    return movieViewModel;
                }).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<MovieEntity> findAll() {
        return movieRepository.findAll()
                .stream().map(movie -> modelMapper.map(movie, MovieEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void seedMovies() throws IOException {
        if (movieRepository.count() == 0){
                MovieEntity[] moviesEntities =
                        gson.fromJson(Files.readString(Path.of(moviesFile.getURI())), MovieEntity[].class);
            DirectorEntity directorEntity = directorService.findByName("steven Spielberg");
            UserEntity creator = userRepository.findByUsername("admin").orElseThrow();


            Arrays.stream(moviesEntities).forEach(movieEntity -> movieEntity.setDirectorEntity(directorEntity).setUserEntity(creator));

            Arrays.stream(moviesEntities)
                        .forEach(movieRepository::save);

        }
    }

    @Override
    public List<MovieEntity> findAllMoviesByGenre(Genre genre) {
        return movieRepository.findAllByGenre_Name(genre);
    }

    @Override
    public MovieEntity findEntityById(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean containsMovie(String name) {
        return movieRepository.findByName(name).isPresent();
    }
}
