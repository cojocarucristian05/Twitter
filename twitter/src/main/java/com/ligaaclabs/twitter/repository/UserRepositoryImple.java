package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImple implements UserRepository {
    private Map<Integer, User> USERS = new HashMap<>();
    private Integer index = 0;

    @Override
    public void createUser(User user) {
        USERS.put(index, user);
        index++;
    }

    @Override
    public boolean findByUsername(User user) {
        return getAllUsers()
                .stream()
                .anyMatch(user1 -> user1.getUsername().equals(user.getUsername()));
    }

    @Override
    public List<User> getAllUsers() {
        return USERS.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean search(String query) {
        return getAllUsers()
                .stream()
                .anyMatch(user1 -> user1.getUsername().equals(query)
                        || user1.getFirstname().equals(query)
                        || user1.getLastname().equals(query));
    }

    @Override
    public List<User> getSearchUsers(String query) {
        return getAllUsers()
                .stream()
                .filter(user1 -> user1.getUsername().equals(query)
                        || user1.getFirstname().equals(query)
                        || user1.getLastname().equals(query))
                .collect(Collectors.toList());
    }

    @Override
    public User searchByUsername(String username) {
        return getAllUsers()
                .stream()
                .filter(user1 -> user1.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addPost(User user, Post post) {
        user.getPosts().add(post);
    }

}
