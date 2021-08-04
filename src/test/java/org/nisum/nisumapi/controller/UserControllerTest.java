package org.nisum.nisumapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.nisum.nisumapi.dto.resquest.UserDTORequest;
import org.nisum.nisumapi.exceptions.BadRequestException;
import org.nisum.nisumapi.exceptions.InternalServerErrorException;
import org.nisum.nisumapi.model.User;
import org.nisum.nisumapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
// @TestPropertySources(locations  = "classpath:application-integrations.properties")
@AutoConfigureTestDatabase
@Sql("/insert_data.sql")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void whenDuplicateEmail_thenReturnInternalServerErrorException() throws Exception {

        UserDTORequest user = new UserDTORequest("Bob", "Jhon@pp.com", "Pasola12", null);
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(user)))
                .andExpect(result -> assertThat(result.getResolvedException() instanceof InternalServerErrorException))
                .andExpect(result -> assertEquals("This email is already registered", result.getResolvedException().getMessage()));
    }

    @Test
    public void givenUsers_whenGetUser_thenReturnStatus200() throws Exception {

        mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("Petter")))
                .andExpect(jsonPath("$[1].name", is("Jhon")));
    }

    @Test
    void givenUser_whenGetUser_thenReturnStatusCode200AndUser() throws Exception {

        mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Petter")));
    }

    @Test
    public void whenValidInput_thenCreateUserAndCreated() throws Exception {

        UserDTORequest user = new UserDTORequest("Bob", "bob@company", "Pasola12", null);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(user)))
                .andExpect(status().isCreated());

        List<User> found = userService.findAll();
        assertThat(found).extracting(User::getName).contains("Bob");
    }

    @Test
    public void whenEmptyEmail_thenReturnBadRequestException() throws Exception {

        UserDTORequest user = new UserDTORequest("Bob", "", "Pasola12", null);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(user)))
                .andExpect(result -> assertThat(result.getResolvedException() instanceof BadRequestException))
                .andExpect(result -> assertEquals("Email is required", result.getResolvedException().getMessage()));
    }

    @Test
    public void whenGetPhones_thenReturnPhones() throws Exception {

        mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON));

        List<User> found = userService.findAll();
        assertThat(found).extracting(User::getPhones);

    }

    @Test
    public void whenGetUserById_thenReturnUserAnd200() throws Exception {

        mockMvc.perform(get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Petter")));
    }
}