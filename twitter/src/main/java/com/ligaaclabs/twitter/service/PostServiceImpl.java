package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.advice.exception.PostNotFoundException;
import com.ligaaclabs.twitter.advice.exception.UserNotFoundException;
import com.ligaaclabs.twitter.mapper.LikeMapper;
import com.ligaaclabs.twitter.mapper.PostMapper;
import com.ligaaclabs.twitter.mapper.ReplyMapper;
import com.ligaaclabs.twitter.model.dto.*;
import com.ligaaclabs.twitter.model.entities.Like;
import com.ligaaclabs.twitter.model.entities.Post;
import com.ligaaclabs.twitter.model.entities.Reply;
import com.ligaaclabs.twitter.model.entities.User;
import com.ligaaclabs.twitter.repository.LikeRepository;
import com.ligaaclabs.twitter.repository.PostRepository;
import com.ligaaclabs.twitter.repository.ReplyRepository;
import com.ligaaclabs.twitter.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final LikeRepository likeRepository;

    private final ReplyRepository replyRepository;

    private final PostMapper postMapper;

    private final LikeMapper likeMapper;

    private final ReplyMapper replyMapper;

    public PostServiceImpl(UserRepository userRepository,
                           PostRepository postRepository,
                           LikeRepository likeRepository,
                           ReplyRepository replyRepository,
                           PostMapper postMapper,
                           LikeMapper likeMapper,
                           ReplyMapper replyMapper) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.replyRepository = replyRepository;
        this.postMapper = postMapper;
        this.likeMapper = likeMapper;
        this.replyMapper = replyMapper;
    }

    @Override
    public ResponseEntity<?> addPost(UUID userId, String content) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Post post = new Post();
        post.setPostDate(LocalDateTime.now());
        post.setContent(content);
        post.setUser(user);
        user.getPosts().add(post);
        postRepository.save(post);
        return ResponseEntity.ok("Post added!");
    }


    @Override
    public List<PostResponseDTO> getOwnPostsByTimestamp(UUID userId, LocalDateTime timestamps) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found!"));

        List<Post> posts = postRepository.findPostsByUser(user);
        if(Objects.isNull(timestamps)) {
            return posts
                    .stream()
                    .map(postMapper::postToPostResponseDTO)
                    .collect(Collectors.toList());
        }

        return posts
                .stream()
                .filter(post -> post.getPostDate().isAfter(timestamps))
                .map(postMapper::postToPostResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTOFeedResponse> getFeed(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found!"));
        List<PostDTOFeedResponse> feed = new ArrayList<>();
        for(User followed : user.getFollowing()) {
            feed.addAll(postRepository.findPostsByUser(followed)
                    .stream()
                    .map(postMapper::postToPostDTOFeedResponse).toList()
            );
        }
        return feed;
    }

    @Override
    public ResponseEntity<?> likePost(LikeDTO likeDTO) {
        Post post = postRepository.findById(likeDTO.getPostId()).orElseThrow(() -> new PostNotFoundException("Post not found!"));
        User user = userRepository.findById(likeDTO.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Like like = new Like();
        like.setPost(post);
        like.setUser(user);
        post.getLikes().add(like);
        post.getUser().getLikes().add(like);
        likeRepository.save(like);
        return ResponseEntity.ok("Like added!");
    }

    @Override
    public ResponseEntity<?> addReply(ReplyDTO replyDTO) {
        User user = userRepository.findById(replyDTO.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found!"));
        Post post = postRepository.findById(replyDTO.getPostId()).orElseThrow(() -> new PostNotFoundException("Post not found!"));
        Reply reply = replyMapper.replyDTOToReply(replyDTO);
        post.getReplies().add(reply);
        user.getReplies().add(reply);
        replyRepository.save(reply);
        System.out.println(reply);
        return ResponseEntity.ok("Reply added!");
    }

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::postToPostResponseDTO)
                .collect(Collectors.toList());
    }
}
