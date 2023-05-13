package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.mapper.UserMapper;
import com.ligaaclabs.twitter.model.dto.UserDTO;
import com.ligaaclabs.twitter.model.entities.User;
import com.ligaaclabs.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
    public List<UserDTO> search(String query) {
        return userRepository.searchByUsernameOrLastnameOrFirstname(query, query, query)
                .stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
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
