package com.example.demo.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = NONE)
class UserControllerTest {

    private Long userTestId;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUserLoginPage() throws Exception {
        mockMvc.perform(get("/users/login")).andExpect(status().isOk());
    }


    @Test
    public void testGetUserRegisterPage() throws Exception {
        mockMvc.perform(get("/users/register")).andExpect(status().isOk());
    }

    @Test
    public void testPostUserRegisterPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users/register")
                .param("username", "ivan")
                .param("email", "ivan@abv.bg")
                .param("password", "121212")
                .param("confirmPassword", "121212")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(mvcResult -> {
                    "/users/login".equals(mvcResult.getModelAndView().getViewName());
                });
    }



}