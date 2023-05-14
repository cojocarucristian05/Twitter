package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.advice.exception.UserNotFoundException;
import com.ligaaclabs.twitter.mapper.LikeMapper;
import com.ligaaclabs.twitter.mapper.PostMapper;
import com.ligaaclabs.twitter.model.dto.LikeDTO;
import com.ligaaclabs.twitter.model.dto.PostDTO;
import com.ligaaclabs.twitter.model.dto.PostResponseDTO;
import com.ligaaclabs.twitter.model.entities.Like;
import com.ligaaclabs.twitter.model.entities.Post;
import com.ligaaclabs.twitter.model.entities.User;
import com.ligaaclabs.twitter.repository.LikeRepository;
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

    private final LikeRepository likeRepository;

    private final PostMapper postMapper;

    private final LikeMapper likeMapper;

    public PostServiceImpl(UserRepository userRepository,
                           PostRepository postRepository,
                           LikeRepository likeRepository,
                           PostMapper postMapper,
                           LikeMapper likeMapper) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.postMapper = postMapper;
        this.likeMapper = likeMapper;
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
        user.getPosts().add(post);
        postRepository.save(post);
        return ResponseEntity.ok("Post added!");
    }


    @Override
    public List<PostResponseDTO> getOwnPostsByTimestamp(String username, LocalDateTime timestamps) {
        List<Post> posts = postRepository.findAll();
        if(Objects.isNull(timestamps)) {
            return posts
                    .stream()
                    .filter(post -> post.getUser().getUsername().equals(username))
                    .map(postMapper::postToPostResponseDTO)
                    .collect(Collectors.toList());
        }

        return posts
                .stream()
                .filter(post -> post.getUser().getUsername().equals(username))
                .filter(post -> post.getDate().isAfter(timestamps))
                .map(postMapper::postToPostResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getFeed(UUID userId) {
        if(userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException("User not found!");
        }
        User user = userRepository.findById(userId).get();
        List<PostDTO> feed = new ArrayList<>();
        for(User followed : user.getFollowing()) {
            System.out.println(followed.getUsername());
            feed.addAll(postRepository.findAll()
                    .stream()
                    .filter(post -> post.getUser().equals(followed))
                    .map(postMapper::postToPostDTO).toList()
            );
        }
        return feed;
    }

    @Override
    public ResponseEntity<?> likePost(LikeDTO likeDTO) {
        if(postRepository.findById(likeDTO.getPostId()).isEmpty()) {
            return ResponseEntity.badRequest().body("Post not found!");
        }

        if(userRepository.findById(likeDTO.getUserId()).isEmpty()) {
            return ResponseEntity.badRequest().body("User not found!");
        }

        Post post = postRepository.findById(likeDTO.getPostId()).get();
        User user = userRepository.findById(likeDTO.getUserId()).get();
        Like like = new Like();
        like.setId(UUID.randomUUID());
        like.setPost(post);
        like.setUser(user);
        post.getLikes().add(like);
        System.out.println(post.getUser());
//        post.getUser().getLikes().add(like);
        likeRepository.save(like);
        return ResponseEntity.ok("Like added!");
    }
    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::postToPostResponseDTO)
                .collect(Collectors.toList());
    }
}
