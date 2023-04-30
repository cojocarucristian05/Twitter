package com.ligaaclabs.twitter.service;


import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import java.util.List;

public interface PostService {
    void addPost(User user, String content);

    List<Post> getAllPosts();
}
