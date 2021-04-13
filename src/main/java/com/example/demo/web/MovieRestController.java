package com.example.demo.web;

import com.example.demo.model.entity.MovieEntity;
import com.example.demo.model.view.MovieViewModel;
import com.example.demo.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/movies")
@RestController
public class MovieRestController {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieRestController(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/api")
    public ResponseEntity<List<MovieViewModel>> findAll() {
        return ResponseEntity
                .ok()
                .body(movieRepository.
                        findAll().
                        stream().
                        map(e -> modelMapper.map(e, MovieViewModel.class)).
                        collect(Collectors.toList()));
    }
}

