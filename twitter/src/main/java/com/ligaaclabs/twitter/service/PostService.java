package com.ligaaclabs.twitter.service;


import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public interface PostService {
    ResponseEntity<?> addPost(UUID userId, String content);
//
//    List<Post> getAllPosts();
//
    List<Post> getOwnPostsByTimestamp(UUID userId, LocalDateTime timestamp);
//
//    List<Post> getOwnPosts(User user);
//
//    Post getPostById(Integer id);

}
