package com.example.demo.web;

import com.example.demo.model.entity.Genre;
import com.example.demo.repository.DirectorsRepository;
import com.example.demo.repository.LogRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = NONE)
class MovieRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DirectorsRepository directorsRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private LogRepository logRepository;


    private MovieTEstData movieTestData;

    @BeforeEach
    public void setUp(){
        movieTestData = new MovieTEstData(
                userRepository,
                directorsRepository,
                movieRepository,
                logRepository
        );
        movieTestData.init();
    }

    @AfterEach
    public void tearDown(){
        movieTestData.cleanUp();
    }

    @Test
    @WithMockUser(value = "Pesho", roles = {"USER", "ADMIN"})
    void testFetchMovies() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/movies/api"))
                .andExpect(status().isOk()).
                andExpect(jsonPath("[0].name").value("Avengers: Infinity War")).
                andExpect(jsonPath("[0].rating").value(9)).
                andExpect(jsonPath("[0].genre").value(Genre.FANTASY.name())).
                andExpect(jsonPath("[1].name").value("The Transporter")).
                andExpect(jsonPath("[1].rating").value(7)).
                andExpect(jsonPath("[1].genre").value(Genre.ACTION.name()));

    }

}