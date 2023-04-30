package com.ligaaclabs.twitter.controller;

import com.ligaaclabs.twitter.advice.exception.UserNotFoundException;
import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import com.ligaaclabs.twitter.service.PostService;
import com.ligaaclabs.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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

    @PostMapping("/add_post/{username}")
    public ResponseEntity<?> addPost(@PathVariable String username, @RequestBody String content) {
        User user = userService.getByUsername(username);
        if(user != null) {
            postService.addPost(user, content);
            return ResponseEntity.ok("Post added!");
        }
        return ResponseEntity.badRequest().body("User not found!");
    }

    @GetMapping("/own/{username}")
    public List<Post> getOwnPosts(@PathVariable String username, @RequestParam(required = false) LocalDateTime timestamp) {
        User user = userService.getByUsername(username);
        List<Post> posts;
        if(user == null) {
            throw new UserNotFoundException("User not found!");
        }
        if (timestamp != null) {
            posts = postService.getOwnPostsByTimestamp(user, timestamp);
        } else {
            posts = postService.getOwnPosts(user);
        }
        return posts;
    }

    @GetMapping("/feed/{username}")
    public List<Post> getFeed(@PathVariable String username) {
        User user = userService.getByUsername(username);
        if(user == null) {
            throw new UserNotFoundException("User not found!");
        }

        List<String> following = user.getFollowing();
        List<Post> feed = new ArrayList<>();
        for (String followed : following) {
            feed.addAll(postService.getOwnPosts(userService.getByUsername(followed)));
        }
        feed.sort(Comparator.comparing(Post::getDate).reversed());
        return feed;
    }

}
