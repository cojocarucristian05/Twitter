package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.Post;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository{

    private Map<Integer, Post> POSTS = new HashMap<>();
    private Integer index = 0;

    @Override
    public void createPost(Post post) {
        POSTS.put(index, post);
        index++;
    }

    @Override
    public List<Post> getAllPosts() {
        return POSTS.values().stream().collect(Collectors.toList());
    }

}
