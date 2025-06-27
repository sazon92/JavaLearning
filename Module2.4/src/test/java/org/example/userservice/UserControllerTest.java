package org.example.userservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.userservice.controller.UserController;
import org.example.userservice.dto.UserDto;
import org.example.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllUsers() throws Exception {
        UserDto user = new UserDto();
        user.setId(1L);
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setAge(30);

        when(service.findAll()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Alice")));
    }

    @Test
    void testCreateUser() throws Exception {
        UserDto input = new UserDto();
        input.setName("Bob");
        input.setEmail("bob@example.com");
        input.setAge(25);

        UserDto saved = new UserDto();
        saved.setId(2L);
        saved.setName("Bob");
        saved.setEmail("bob@example.com");
        saved.setAge(25);

        when(service.create(Mockito.any())).thenReturn(saved);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("Bob")));
    }

    @Test
    void testGetUserById() throws Exception {
        UserDto user = new UserDto();
        user.setId(1L);
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setAge(30);

        when(service.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Alice")))
                .andExpect(jsonPath("$.email", is("alice@example.com")))
                .andExpect(jsonPath("$.age", is(30)));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserDto input = new UserDto();
        input.setName("Updated");
        input.setEmail("updated@example.com");
        input.setAge(35);

        UserDto updated = new UserDto();
        updated.setId(1L);
        updated.setName("Updated");
        updated.setEmail("updated@example.com");
        updated.setAge(35);

        when(service.update(Mockito.eq(1L), Mockito.any())).thenReturn(updated);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated")))
                .andExpect(jsonPath("$.email", is("updated@example.com")))
                .andExpect(jsonPath("$.age", is(35)));
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());

        Mockito.verify(service).delete(1L);
    }

}
