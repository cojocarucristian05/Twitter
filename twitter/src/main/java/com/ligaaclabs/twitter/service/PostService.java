package com.ligaaclabs.twitter.service;


import com.ligaaclabs.twitter.model.dto.*;
import com.ligaaclabs.twitter.model.entities.Post;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PostService {
    ResponseEntity<?> addPost(UUID userId, String content);
    List<PostResponseDTO> getAllPosts();
    List<PostResponseDTO> getOwnPostsByTimestamp(UUID userId, LocalDateTime timestamps);
    List<PostDTOFeedResponse> getFeed(UUID userId);

    ResponseEntity<?> likePost(LikeDTO likeDTO);

    ResponseEntity<?> addReply(ReplyDTO replyDTO);
}
