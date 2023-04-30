package com.ligaaclabs.twitter.controller;

import com.ligaaclabs.twitter.model.Post;
import com.ligaaclabs.twitter.model.User;
import com.ligaaclabs.twitter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    public final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> getAllPost() {
        return postService.getAllPosts();
    }
}
