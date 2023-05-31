package com.ligaaclabs.twitter.controller;

import com.ligaaclabs.twitter.model.dto.UserDTO;
import com.ligaaclabs.twitter.model.dto.UserRegisterDTO;
import com.ligaaclabs.twitter.service.LikeService;
import com.ligaaclabs.twitter.service.PostService;
import com.ligaaclabs.twitter.service.ReplyService;
import com.ligaaclabs.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {


    private final UserService userService;

    private final PostService postService;

    private final LikeService likeService;

    private final ReplyService replyService;

    @Autowired
    public UserController(UserService userService, PostService postService, LikeService likeService, ReplyService replyService) {
        this.userService = userService;
        this.postService = postService;
        this.likeService = likeService;
        this.replyService = replyService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.registerUser(userRegisterDTO);
    }

    @GetMapping()
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/search/{query}/{pageable}")
    public Page<UserDTO> getSearchUsers(@PathVariable String query, @PathVariable Pageable pageable) {
        return userService.search(query, pageable);
    }

    @GetMapping("/search/{query}")
    public List<UserDTO> getSearchUsers(@PathVariable String query) {
        return userService.search(query);
    }
    @PostMapping("/{idFollower}/follow/{idFollowed}")
    public ResponseEntity<?> follow(@PathVariable UUID idFollower, @PathVariable UUID idFollowed) {
       return userService.follow(idFollower, idFollowed);
    }

    @PostMapping("/{idFollower}/unfollow/{idFollowed}")
    public ResponseEntity<?> unfollow(@PathVariable UUID idFollower, @PathVariable UUID idFollowed) {
        return userService.unfollow(idFollower, idFollowed);
    }

    @DeleteMapping("/unregister{userId}")
    public ResponseEntity<?> unregister(@PathVariable UUID userId) {
        return userService.unregister(userId);
    }
}
