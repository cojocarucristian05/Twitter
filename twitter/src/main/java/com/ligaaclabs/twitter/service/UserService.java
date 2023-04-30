package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface UserService {

    boolean searchByUsername(User user);
    void registerUser(User user);

    List<User> getAllUsers();

    List<User> getSearchUsers(String query);

    boolean search(String query);

     boolean follow(String usernameFollower, String usernameFollowed);

    void addPost(User user, Post post);

    User getByUsername(String username);
}
