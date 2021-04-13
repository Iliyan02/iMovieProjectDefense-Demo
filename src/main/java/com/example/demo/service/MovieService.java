package com.example.demo.service;

import com.example.demo.model.entity.Genre;
import com.example.demo.model.entity.MovieEntity;
import com.example.demo.model.service.MovieServiceModel;
import com.example.demo.model.view.MovieViewModel;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    void createMovie(MovieServiceModel movieServiceModel);

    MovieViewModel findById(Long id);

    List<MovieEntity> findAll();

    void deleteMovie(Long id);

    void seedMovies() throws IOException;

    List<MovieEntity> findAllMoviesByGenre(Genre genre);

    MovieEntity findEntityById(Long movieId);

    boolean containsMovie(String name);
}
