package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.advice.exception.UserNotFoundException;
import com.ligaaclabs.twitter.mapper.UserMapper;
import com.ligaaclabs.twitter.model.dto.UserDTO;
import com.ligaaclabs.twitter.model.dto.UserRegisterDTO;
import com.ligaaclabs.twitter.model.entities.User;
import com.ligaaclabs.twitter.repository.PostRepository;
import com.ligaaclabs.twitter.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PostRepository postRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, PostRepository postRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean search(User user) {
        return userRepository.existsById(user.getUserId());
    }

    @Override
    public ResponseEntity<?> registerUser(UserRegisterDTO userRegisterDTO) {
        if (userRepository.findUserByUsername(userRegisterDTO.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists!");
        }
        User user = userMapper.userRegisterDTOToUser(userRegisterDTO);
        user.setUserId(UUID.randomUUID());
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
            User follower  = userRepository.findById(idFollower).get();
            User followed  = userRepository.findById(idFollowed).get();
            if(follower.getFollowing().contains(followed)) {
                return ResponseEntity.badRequest().body("You already follow this account!");
            }
            follower.getFollowing().add(followed);
            followed.getFollowers().add(follower);
            return ResponseEntity.ok("Follow succeed!");
        }
        return ResponseEntity.badRequest().body("Follow denied!");
    }

    @Override
    public ResponseEntity<?> unfollow(UUID idFollower, UUID idFollowed) {
        if (userRepository.findById(idFollower).isPresent()
                && userRepository.findById(idFollowed).isPresent()) {
            User follower  = userRepository.findById(idFollower).get();
            User followed  = userRepository.findById(idFollowed).get();
            if(follower.getFollowing().contains(followed)) {
                follower.getFollowing().remove(followed);
                followed.getFollowers().remove(follower);
                return ResponseEntity.ok("Unfollow succeed!");
            }
        }
        return ResponseEntity.badRequest().body("Unfollow denied!");
    }

    @Override
    public ResponseEntity<?> unregister(UUID userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        User user = userRepository.findById(userId).get();
        postRepository.deleteAll(user.getPosts());
        userRepository.delete(user);
        for (User follower : user.getFollowers()) {
            follower.getFollowing().remove(user);
        }
        for (User followed : user.getFollowing()) {
            followed.getFollowers().remove(user);
        }
        return ResponseEntity.ok("User deleted!");
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }
}
