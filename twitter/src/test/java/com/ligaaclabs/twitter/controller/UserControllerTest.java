package com.ligaaclabs.twitter.controller;

import com.ligaaclabs.twitter.model.dto.UserDTO;
import com.ligaaclabs.twitter.model.dto.UserRegisterDTO;
import com.ligaaclabs.twitter.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerUser() throws Exception {
        UserRegisterDTO userRegisterDTO =
                new UserRegisterDTO("john_doe",
                                    "firstname",
                                    "lastname",
                                    "email",
                                    "password123");

        when(userService.registerUser(any(UserRegisterDTO.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"john_doe\",\"password\":\"password123\"}"))
                .andExpect(status().isCreated());

        verify(userService).registerUser(any(UserRegisterDTO.class));
    }

    @Test
    void getAllUsers() throws Exception {
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO(UUID.randomUUID(), "john_doe", "john", "doe"));
        users.add(new UserDTO(UUID.randomUUID(), "cristiano", "cristiano", "ronaldo"));

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("john_doe"))
                .andExpect(jsonPath("$[1].username").value("cristiano"));

        verify(userService).getAllUsers();
    }

    @Test
    void getSearchUsers() throws Exception {
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO(UUID.randomUUID(), "john_doe", "john", "doe"));
        users.add(new UserDTO(UUID.randomUUID(), "cristiano", "cristiano", "ronaldo"));

        when(userService.search(anyString())).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/search/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("john_doe"))
                .andExpect(jsonPath("$[1].username").value("cristiano"));

        verify(userService).search(anyString());
    }

    @Test
    void follow() throws Exception {
        UUID followerId = UUID.randomUUID();
        UUID followedId = UUID.randomUUID();

        when(userService.follow(any(UUID.class), any(UUID.class))).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/{idFollower}/follow/{idFollowed}", followerId, followedId))
                .andExpect(status().isOk());

        verify(userService).follow(eq(followerId), eq(followedId));
    }

    @Test
    void unfollow() throws Exception {
        UUID followerId = UUID.randomUUID();
        UUID followedId = UUID.randomUUID();

        when(userService.unfollow(any(UUID.class), any(UUID.class))).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/{idFollower}/unfollow/{idFollowed}", followerId, followedId))
                .andExpect(status().isOk());

        verify(userService).unfollow(eq(followerId), eq(followedId));
    }

    @Test
    void unregister() throws Exception {
        UUID userId = UUID.randomUUID();

        when(userService.unregister(any(UUID.class))).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/unregister/{userId}", userId))
                .andExpect(status().isOk());

        verify(userService).unregister(eq(userId));
    }
}