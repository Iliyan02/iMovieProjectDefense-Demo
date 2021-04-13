package com.example.demo.web;

import com.example.demo.model.entity.Genre;
import com.example.demo.repository.DirectorsRepository;
import com.example.demo.repository.LogRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = NONE)
class  MovieControllerTest {

    private static  final String MOVIE_CONTROLLER_PREFIX = "/movies";

    private long testMovieId;

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

    private MovieTEstData movieTEstData;

    @BeforeEach
    private void setup(){
        movieTEstData = new MovieTEstData(
                userRepository,
                directorsRepository,
                movieRepository,
                logRepository
        );
        movieTEstData.init();
        testMovieId = movieTEstData.getTestMovieId();
    }

    @AfterEach
    public void tearDown(){
        movieTEstData.cleanUp();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(
                MOVIE_CONTROLLER_PREFIX + "/movie-page-details/{id}", testMovieId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("movie-page-details")).
                andExpect(model().attributeExists("movie"));
    }

    @Test
    void testShouldReturnInValidStatusViewNameAndModel() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(
                MOVIE_CONTROLLER_PREFIX + "/movie-page-details/{id}", testMovieId
        )).
                andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void addMovie() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post(MOVIE_CONTROLLER_PREFIX + "/add")
        .param("name", "test movie").
                param("genre", Genre.ACTION.name()).
                param("imageUrl", "https://melange.bg/wp-content/uploads/2018/05/poster.jpg").
                param("backgroundImage", "https://i.ytimg.com/vi/QwievZ1Tx-8/maxresdefault.jpg").
                param("description", "Some description").
                param("videoUrl", "6ZfuNTqbHE8").
                param("releaseDate", "2000-01-01").
                param("duration", "160").
                param("rating", "8").
                param("genre2", Genre.FANTASY.name()).
                param("director", "steven Spielberg").
                with(csrf())).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(9, movieRepository.count());
    }

    @Test
    @DirtiesContext
    @WithMockUser(username="Pesho",authorities={"USER"})
    public void getAddMoviePageWithoutAdminAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/add)").with(csrf()))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @DirtiesContext
    public void getAddMoviePageWhenNotLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/movie/add"))
                .andExpect(status().is3xxRedirection());
    }



    @Test
    @DirtiesContext
    @WithMockUser(username = "Pesho", authorities = {"USER"})
    public void getMovieDetailsPageWhenLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/movies/movie-page-details/{id}", testMovieId))
                .andExpect(status().isOk())
                .andExpect(view().name("movie-page-details"))
                .andExpect(model().attributeExists("movie"));
    }

    @Test
    @DirtiesContext
    @WithMockUser(username = "Pesho", authorities = {"USER", "ADMIN"})
    public void getMovieDetailsPageWhenLoggedWithAdminRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/movies/movie-page-details/{id}", testMovieId))
                .andExpect(status().isOk())
                .andExpect(view().name("movie-page-details"))
                .andExpect(model().attributeExists("movie"));

    }

    @Test
    @DirtiesContext
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void getDeleteMovieWhenLoggedAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/movies/delete"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void getDeleteMovieWhenNotLoggedAdminError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/movies/delete"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void shouldReturnAllDirectors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(MOVIE_CONTROLLER_PREFIX + "/add")).
                andExpect(status().isOk())
                .andExpect(view().name("add-movie"))
                .andExpect(model().attributeExists("directors"));
    }
}