package com.ligaaclabs.twitter.controller;

import com.ligaaclabs.twitter.model.dto.LikeDTO;
import com.ligaaclabs.twitter.model.dto.PostDTO;
import com.ligaaclabs.twitter.model.dto.PostResponseDTO;
import com.ligaaclabs.twitter.model.dto.ReplyDTO;
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
    public List<PostResponseDTO> getOwnPosts(@PathVariable UUID userId, @RequestParam(required = false) LocalDateTime timestamp) {
        return postService.getOwnPostsByTimestamp(userId, timestamp);
    }

    @GetMapping("/{userId}/feed")
    public List<PostResponseDTO> getFeed(@PathVariable UUID userId) {
        return postService.getFeed(userId);
    }

    @PostMapping("/like")
    public ResponseEntity<?> likePost(@RequestBody LikeDTO likeDTO){
        return postService.likePost(likeDTO);
    }
    @GetMapping
    public List<PostResponseDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/reply")
    public ResponseEntity<?> replyPost(@RequestBody ReplyDTO replyDTO) {
        return postService.addReply(replyDTO);
    }
}
