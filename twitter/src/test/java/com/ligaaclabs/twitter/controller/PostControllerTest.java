package com.ligaaclabs.twitter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ligaaclabs.twitter.model.dto.LikeDTO;
import com.ligaaclabs.twitter.model.dto.PostResponseDTO;
import com.ligaaclabs.twitter.service.PostService;
import com.ligaaclabs.twitter.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private UserService userService;

    @Test
    void addPost() throws Exception {
        UUID userId = UUID.randomUUID();
        String content = "Test post content";

        when(postService.addPost(eq(userId), eq(content))).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/posts/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        verify(postService).addPost(eq(userId), eq(content));
    }

    @Test
    void getOwnPosts() throws Exception {
        UUID userId = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();

        List<PostResponseDTO> posts = createDummyPosts();

        when(postService.getOwnPostsByTimestamp(eq(userId), eq(timestamp))).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/posts/{userId}/posts", userId)
                        .param("timestamp", timestamp.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(posts.size())));

        verify(postService).getOwnPostsByTimestamp(eq(userId), eq(timestamp));
    }

    @Test
    void likePost() throws Exception {
        LikeDTO likeDTO = new LikeDTO(UUID.randomUUID(), UUID.randomUUID());

        when(postService.likePost(eq(likeDTO))).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/posts/like")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(likeDTO)))
                .andExpect(status().isOk());

        verify(postService).likePost(eq(likeDTO));
    }

    @Test
    void getAllPosts() throws Exception {
        List<PostResponseDTO> posts = createDummyPosts();

        when(postService.getAllPosts()).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(posts.size())));

        verify(postService).getAllPosts();
    }

    // Helper method to convert objects to JSON string
    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private List<PostResponseDTO> createDummyPosts() {
        List<PostResponseDTO> posts = new ArrayList<>();

        UUID post1Id = UUID.randomUUID();
        String post1Content = "Post 1 content";
        PostResponseDTO post1 = new PostResponseDTO(post1Id, post1Content, LocalDateTime.now(), new ArrayList<>(), new ArrayList<>());
        posts.add(post1);

        UUID post2Id = UUID.randomUUID();
        String post2Content = "Post 2 content";
        PostResponseDTO post2 = new PostResponseDTO(post2Id, post2Content, LocalDateTime.now(), new ArrayList<>(), new ArrayList<>());
        posts.add(post2);

        return posts;
    }
}
