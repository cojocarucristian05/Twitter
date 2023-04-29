package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.User;
import com.ligaaclabs.twitter.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean searchByUsername(User user) {
        return userRepository.findByUsername(user);
    }

    @Override
    public void registerUser(User user) {
        userRepository.createUser(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public List<User> getSearchUsers(String query) {
        return userRepository.getSearchUsers(query);
    }

    @Override
    public boolean search(String query) {
        return userRepository.search(query);
    }
}
