package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import com.ligaaclabs.twitter.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void addPost(User user, String content) {
        Post post = new Post(content, LocalDate.now(), user.getUsername());
        user.getPosts().add(post);
        postRepository.createPost(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }

}
