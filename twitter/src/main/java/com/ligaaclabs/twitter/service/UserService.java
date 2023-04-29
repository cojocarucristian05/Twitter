package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface UserService {

    boolean searchByUsername(User user);
    void registerUser(User user);

    List<User> getAllUsers();

    List<User> getSearchUsers(String query);

    boolean search(String query);
}
