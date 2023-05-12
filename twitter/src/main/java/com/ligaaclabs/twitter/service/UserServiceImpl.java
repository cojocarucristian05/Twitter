package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.entities.User;
import com.ligaaclabs.twitter.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean search(User user) {
        return userRepository.existsById(user.getUserId());
    }

    @Override
    public ResponseEntity<?> registerUser(User user) {
        if (search(user)) {
            return ResponseEntity.badRequest().body("User already exists!");
        }

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @Override
    public List<User> search(String query) {
        return userRepository.searchByUsernameOrLastnameOrFirstname(query, query, query);
    }

//    public List<User> getAllUsers() {
//        return userRepository.getAllUsers();
//    }
//
//    @Override
//    public List<User> getSearchUsers(String query) {
//        return userRepository.getSearchUsers(query);
//    }
//
//    @Override
//    public boolean search(String query) {
//        return userRepository.search(query);
//    }

    @Override
    public ResponseEntity<?> follow(UUID idFollower, UUID idFollowed) {
        if (userRepository.findById(idFollower).isPresent()
                && userRepository.findById(idFollowed).isPresent()) {
            User followed  = userRepository.findById(idFollowed).get();
            User follower  = userRepository.findById(idFollower).get();
            followed.getFollowers().add(follower);
            follower.getFollowing().add(followed);
            userRepository.save(followed);
            userRepository.save(follower);
            return ResponseEntity.ok("Follow succeed!");
        }
        return ResponseEntity.badRequest().body("Follow denied!");
    }

//    @Override
//    public void addPost(User user, Post post) {
//        userRepository.addPost(user, post);
//    }
//
//    @Override
//    public User getByUsername(String username) {
//        return userRepository.searchByUsername(username);
//    }
}
