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
class PolicyTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    public void getPolicyContentWhenNotLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/policy/content"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DirtiesContext
    public void getPolicyPrivacyWhenNotLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/policy/privacy"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void getPolicyAboutUsWhenNotLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/policy/aboutUs"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void getPolicyPrivacyWhenLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/policy/privacy"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void getPolicyAboutUsWhenLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/policy/aboutUs"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void getPolicyContentWhenLogged() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/policy/content"))
                .andExpect(status().isOk());
    }

}