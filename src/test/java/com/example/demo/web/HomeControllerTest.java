package com.example.demo.web;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = NONE)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    public void getHomeWhenNotLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/home"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DirtiesContext
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void getHomeWhenLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/home"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void getIndexWhenNotLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void getActionGenreWhenLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/genre/action"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void getActionGenreWhenLoggedError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/genre/action"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DirtiesContext
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void getFantasyGenreWhenLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/genre/fantasy"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void getFantasyGenreWhenLoggedError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/genre/fantasy"))
                .andExpect(status().is3xxRedirection());
    }



}