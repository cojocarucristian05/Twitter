package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository {

    void createPost(Post post);

    List<Post> getAllPosts();

    public List<Post> getOwnPostsByTimestamp(User user, LocalDateTime timestamps);

    List<Post> getOwnPosts(User user);

}
