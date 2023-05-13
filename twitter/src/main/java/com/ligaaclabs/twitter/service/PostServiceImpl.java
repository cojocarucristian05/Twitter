package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.advice.exception.UserNotFoundException;
import com.ligaaclabs.twitter.mapper.PostMapper;
import com.ligaaclabs.twitter.model.dto.PostDTO;
import com.ligaaclabs.twitter.model.entities.Post;
import com.ligaaclabs.twitter.model.entities.User;
import com.ligaaclabs.twitter.repository.PostRepository;
import com.ligaaclabs.twitter.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final PostMapper postMapper;

    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository, PostMapper postMapper) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public ResponseEntity<?> addPost(UUID userId, String content) {
        if(userRepository.findById(userId).isEmpty()) {
            return ResponseEntity.badRequest().body("User not found!");
        }

        User user = userRepository.findById(userId).get();
        Post post = new Post();
        post.setPostDate(LocalDateTime.now());
        post.setId(UUID.randomUUID());
        post.setContent(content);
        post.setUser(user);
        postRepository.save(post);
        return ResponseEntity.ok("Post added!");
    }


    @Override
    public List<PostDTO> getOwnPostsByTimestamp(UUID userId, LocalDateTime timestamps) {
        List<Post> posts = postRepository.findPostsByUserId(userId);
        if(Objects.isNull(timestamps)) {
            return postRepository.findPostsByUserId(userId)
                    .stream()
                    .map(postMapper::postToPostDTO)
                    .collect(Collectors.toList());
        }

        return posts
                .stream()
                .filter(post -> post.getDate().isAfter(timestamps))
                .map(postMapper::postToPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getFeed(UUID userId) {
        if(!userRepository.findById(userId).isPresent()) {
            throw new UserNotFoundException("User not found!");
        }
        User user = userRepository.findById(userId).get();
        List<PostDTO> feed = new ArrayList<>();
        for(User followed : user.getFollowing()) {
            System.out.println(followed.getUsername());
            feed.addAll(postRepository.findPostsByUserId(followed.getUserId())
                    .stream()
                    .map(postMapper::postToPostDTO).toList()
            );
        }
        return feed;
    }
//    @Override
//    public List<PostDTO> getAllPosts() {
//        return postRepository.findAll()
//                .stream()
//                .map(postMapper::postToPostDTO)
//                .collect(Collectors.toList());
//    }
}
