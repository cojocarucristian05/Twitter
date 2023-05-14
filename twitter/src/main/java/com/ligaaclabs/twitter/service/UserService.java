package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.dto.UserDTO;
import com.ligaaclabs.twitter.model.dto.UserRegisterDTO;
import com.ligaaclabs.twitter.model.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    boolean search(User user);
    ResponseEntity<?> registerUser(UserRegisterDTO userRegisterDTO);
    List<UserDTO> getAllUsers();
    List<UserDTO> search(String query);
    ResponseEntity<?> follow(UUID idFollower, UUID idFollowed);
    ResponseEntity<?> unfollow(UUID idFollower, UUID idFollowed);

    ResponseEntity<?> unregister(UUID userId);
}
