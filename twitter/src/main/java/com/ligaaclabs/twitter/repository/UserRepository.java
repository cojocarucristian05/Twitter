package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    void createUser(User user);
    boolean findByUsername(User user);

    List<User> getAllUsers();

    boolean search(String query);

    List<User> getSearchUsers(String query);
}
