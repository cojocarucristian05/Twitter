package com.ligaaclabs.twitter.service;


import com.ligaaclabs.twitter.model.entities.Post;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PostService {
    ResponseEntity<?> addPost(UUID userId, String content);
//    void addPost(User user, Integer id, String content);
//
//    List<Post> getAllPosts();
//
    List<Post> getOwnPostsByTimestamp(UUID userId, LocalDateTime timestamps);
//
//    List<Post> getOwnPosts(User user);
//
//    Post getPostById(Integer id);

}
