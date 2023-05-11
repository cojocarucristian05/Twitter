package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public interface UserService {

    boolean search(User user);
    ResponseEntity<?> registerUser(User user);

//    List<User> getAllUsers();
//
    List<User> search(String query);
//
//    boolean search(String query);
//
    ResponseEntity<?> follow(UUID idFollower, UUID idFollowed);
//
//    void addPost(User user, Post post);
//
//    User getByUsername(String username);
}
