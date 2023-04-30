package com.ligaaclabs.twitter.service;


import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {
    void addPost(User user, String content);

    List<Post> getAllPosts();

    List<Post> getOwnPostsByTimestamp(User user, LocalDateTime timestamps);

    List<Post> getOwnPosts(User user);

}
