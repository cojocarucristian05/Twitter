package com.ligaaclabs.twitter.controller;

import com.ligaaclabs.twitter.model.entities.User;
import com.ligaaclabs.twitter.service.LikeService;
import com.ligaaclabs.twitter.service.PostService;
import com.ligaaclabs.twitter.service.ReplyService;
import com.ligaaclabs.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {


    private final UserService userService;

    private final PostService postService;

    private final LikeService likeService;

    private final ReplyService replyService;

    @Autowired
    public UserController(UserService userService, PostService postService, LikeService likeService, ReplyService replyService) {
        this.userService = userService;
        this.postService = postService;
        this.likeService = likeService;
        this.replyService = replyService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
//
//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
    @GetMapping("/search/{query}")
    public List<User> getSearchUsers(@PathVariable String query) {
        return userService.search(query);
    }
//
    @PostMapping("/{idFollower}/follow/{idFollowed}")
    public ResponseEntity<?> follow(@PathVariable UUID idFollower, @PathVariable UUID idFollowed) {
       return userService.follow(idFollower, idFollowed);
    }
//
//    @PostMapping("/add_post/{username}")
//    public ResponseEntity<?> addPost(@PathVariable String username, @RequestBody String content) {
//        User user = userService.getByUsername(username);
//        if(user != null) {
//            postService.addPost(user, 0, content);
//            return ResponseEntity.ok("Post added!");
//        }
//        return ResponseEntity.badRequest().body("User not found!");
//    }
//
//    @GetMapping("/own/{username}")
//    public List<Post> getOwnPosts(@PathVariable String username, @RequestParam(required = false) LocalDateTime timestamp) {
//        User user = userService.getByUsername(username);
//        List<Post> posts;
//        if(user == null) {
//            throw new UserNotFoundException("User not found!");
//        }
//        if (timestamp != null) {
//            posts = postService.getOwnPostsByTimestamp(user, timestamp);
//        } else {
//            posts = postService.getOwnPosts(user);
//        }
//        return posts;
//    }
//
//    @GetMapping("/feed/{username}")
//    public List<Post> getFeed(@PathVariable String username) {
//        User user = userService.getByUsername(username);
//        if(user == null) {
//            throw new UserNotFoundException("User not found!");
//        }
//
//        List<String> following = user.getFollowing();
//        List<Post> feed = new ArrayList<>();
//        for (String followed : following) {
//            feed.addAll(postService.getOwnPosts(userService.getByUsername(followed)));
//        }
//        feed.sort(Comparator.comparing(Post::getDate).reversed());
//        return feed;
//    }
//
//
//    @PostMapping(value = "/like_post/{id}")
//    public ResponseEntity<?> likePost(@PathVariable Integer id, @RequestParam String username) {
//        Post post = postService.getPostById(id);
//        if(Objects.isNull(post)) {
//            throw new PostNotFoundException("Post not found!");
//        }
//
//        for(Like like : post.getLikes()) {
//            if(like.getUsername().equals(username)){
//                return ResponseEntity.badRequest().body("You already liked this post!");
//            }
//        }
//
//        User user = userService.getByUsername(username);
//        if(Objects.isNull(user)){
//            throw new UserNotFoundException("User not found!");
//        }
//
//        Like like = new Like(post.getId(), user.getUsername());
//
//        likeService.createLike(like);
//        post.getLikes().add(like);
//        user.getLikes().add(like);
//        return ResponseEntity.ok("Like added!");
//    }
//
//    @GetMapping("/post/{id}")
//    public User getUserByPost(@PathVariable Integer id) {
//        Post post = postService.getPostById(id);
//        if(Objects.isNull(post)) {
//            throw new PostNotFoundException("Post not found!");
//        }
//
//        return userService.getByUsername(post.getUser());
//    }
//
//    @PostMapping("{username}/reply/{postId}")
//    public ResponseEntity<?> reply(@PathVariable String username,
//                                   @PathVariable Integer postId,
//                                   @RequestParam(required = false) Integer replyParentId,
//                                   @RequestBody Reply reply) {
//
//        Post post = postService.getPostById(postId);
//        if(Objects.isNull(post)) {
//            throw new PostNotFoundException("Post not found !");
//        }
//
//        User user = userService.getByUsername(username);
//        if(Objects.isNull(user)) {
//            throw new UserNotFoundException("User not found!");
//        }
//
//        reply.setUsername(username);
//
//        if(replyParentId != null) {
//            Reply parentReply = replyService.getReplyByParentId(replyParentId);
//            if (parentReply == null || parentReply.getParentPostId() != postId) {
//                return ResponseEntity.badRequest().body("Reply not found!");
//            }
//            reply.setParentReplyId(parentReply.getId());
//            parentReply.getReplies().add(reply.getContent());
//        } else {
//            reply.setParentPostId(postId);
//            post.getReplies().add(reply);
//        }
//        replyService.addReply(reply);
//        return ResponseEntity.ok("Reply added!");
//    }
}
