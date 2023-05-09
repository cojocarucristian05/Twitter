package com.ligaaclabs.twitter.controller;

import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.service.PostService;
import com.ligaaclabs.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    public final PostService postService;

    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("{userId}")
    public ResponseEntity<?> addPost(@PathVariable UUID userId, @RequestBody String content) {
        return postService.addPost(userId, content);
    }

    @GetMapping("/own/{userId}")
    public List<Post> getOwnPosts(@PathVariable UUID userId, @RequestParam(required = false) LocalDateTime timestamp) {
        return postService.getOwnPostsByTimestamp(userId, timestamp);
    }
}
