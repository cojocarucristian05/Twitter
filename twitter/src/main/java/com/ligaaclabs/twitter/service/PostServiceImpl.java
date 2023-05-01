package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import com.ligaaclabs.twitter.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void addPost(User user, Integer id, String content) {
        Post post = new Post(id, content, LocalDateTime.now(), user.getUsername());
        user.getPosts().add(post);
        postRepository.createPost(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }

    @Override
    public List<Post> getOwnPostsByTimestamp(User user, LocalDateTime timestamps) {
        return postRepository.getOwnPostsByTimestamp(user, timestamps);
    }

    @Override
    public List<Post> getOwnPosts(User user) {
        return postRepository.getOwnPosts(user);
    }

    @Override
    public Post getPostById(Integer id) {
        return postRepository.getPostById(id);
    }

}
