package com.example.demo.service;

import com.example.demo.model.entity.MovieEntity;
import com.example.demo.model.service.MovieServiceModel;
import com.example.demo.model.view.MovieViewModel;
import com.example.demo.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    private MovieEntity movieEntity;
    private MovieServiceModel movieServiceModel;
    private MovieViewModel movieViewModel;





}