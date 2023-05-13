package com.ligaaclabs.twitter.controller;

import com.ligaaclabs.twitter.model.dto.PostDTO;
import com.ligaaclabs.twitter.model.entities.Post;
import com.ligaaclabs.twitter.service.PostService;
import com.ligaaclabs.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.SpinnerUI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    public final PostService postService;

    public final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("{userId}")
    public ResponseEntity<?> addPost(@PathVariable UUID userId, @RequestBody String content) {
        return postService.addPost(userId, content);
    }

    @GetMapping("/{userId}/posts")
    public List<PostDTO> getOwnPosts(@PathVariable UUID userId, @RequestParam(required = false) LocalDateTime timestamp) {
        return postService.getOwnPostsByTimestamp(userId, timestamp);
    }

    @GetMapping("/{userId}/feed")
    public List<PostDTO> getFeed(@PathVariable UUID userId) {
        return postService.getFeed(userId);
    }

//    @GetMapping
//    public List<PostDTO> getAllPosts() {
//        return postService.getAllPosts();
//    }
}
