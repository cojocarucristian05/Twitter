package com.ligaaclabs.twitter.controller;

import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import com.ligaaclabs.twitter.service.PostService;
import com.ligaaclabs.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.searchByUsername(user)) {
            return ResponseEntity.badRequest().body("User already exists!");
        }
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/search/{query}")
    public List<User> getSearchUsers(@PathVariable String query) {
        return userService.getSearchUsers(query);
    }

    @PostMapping("/{usernameFollower}/follow/{usernameFollowed}")
    public ResponseEntity<?> follow(@PathVariable String usernameFollower, @PathVariable String usernameFollowed) {
        if(userService.follow(usernameFollower, usernameFollowed)) {
           return ResponseEntity.ok("Follow succeed!");
        }
        return ResponseEntity.badRequest().body("Follow denied!");
    }

    @PostMapping("/add/{username}")
    public ResponseEntity<?> addPost(@PathVariable String username, @RequestBody String content) {
        User user = userService.getByUsername(username);
        if(user != null) {
            postService.addPost(user, content);
            return ResponseEntity.ok("Post added!");
        }
        return ResponseEntity.badRequest().body("User not found!");
    }

}
