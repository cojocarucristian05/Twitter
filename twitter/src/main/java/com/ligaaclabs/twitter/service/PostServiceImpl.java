package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.advice.exception.UserNotFoundException;
import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import com.ligaaclabs.twitter.repository.PostRepository;
import com.ligaaclabs.twitter.repository.UserRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public ResponseEntity<?> addPost(UUID userId, String content) {
        if(userRepository.findById(userId).isEmpty()) {
            return ResponseEntity.badRequest().body("User not found!");
        }

        User user = userRepository.findById(userId).get();
        Post post = new Post();
        post.setDate(LocalDateTime.now());
        post.setId(UUID.randomUUID());
        post.setContent(content);
        post.setUser(user);
        postRepository.save(post);
        return ResponseEntity.ok("Post added!");
    }

//    @Override
//    public List<Post> getAllPosts() {
//        return postRepository.getAllPosts();
//    }
//
    @Override
    public List<Post> getOwnPostsByTimestamp(UUID userId, LocalDateTime timestamp) {
        if(userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        User user = userRepository.findById(userId).get();
        List<Post> posts = postRepository.findByUserId(userId);
        if (!Objects.isNull(timestamp)) {
            posts = posts
                    .stream()
                    .filter(post -> post.getDate().isAfter(timestamp))
                    .collect(Collectors.toList());
        }
        return posts;
    }
//
//    @Override
//    public List<Post> getOwnPosts(User user) {
//        return postRepository.getOwnPosts(user);
//    }
//
//    @Override
//    public Post getPostById(Integer id) {
//        return postRepository.getPostById(id);
//    }

}
