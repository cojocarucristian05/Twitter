package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
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

    @Override
    public List<Post> getOwnPostsByTimestamp(User user, LocalDateTime timestamps) {
        return POSTS.values()
                .stream()
                .filter(post -> post.getUser().equals(user.getUsername()))
                .filter(post -> post.getDate().isAfter(timestamps))
                .sorted(Comparator.comparing(Post::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getOwnPosts(User user) {
        return POSTS.values()
                .stream()
                .filter(post -> post.getUser().equals(user.getUsername()))
                .sorted(Comparator.comparing(Post::getDate).reversed())
                .collect(Collectors.toList());
    }


}
