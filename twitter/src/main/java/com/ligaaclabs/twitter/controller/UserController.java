package com.ligaaclabs.twitter.controller;

import com.ligaaclabs.twitter.model.User;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.searchByUsername(user)) {
            return ResponseEntity.badRequest().body("Username already exists!");
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
}
