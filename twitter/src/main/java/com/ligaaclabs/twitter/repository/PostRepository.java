package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.Post;

import java.util.List;

public interface PostRepository {

    void createPost(Post post);

    List<Post> getAllPosts();

}
