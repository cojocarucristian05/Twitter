package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.User;
import com.ligaaclabs.twitter.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean search(User user) {
        return userRepository.existsById(user.getUserId());
    }

    @Override
    public ResponseEntity<?> registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists!");
        }

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @Transactional
    @Override
    public List<User> getSearchUsers(String query) {
        return userRepository.findByUsernameOrFirstnameOrLastname(query, query, query);
    }

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

}
