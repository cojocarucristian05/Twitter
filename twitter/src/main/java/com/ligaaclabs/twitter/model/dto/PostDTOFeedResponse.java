package com.ligaaclabs.twitter.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PostDTOFeedResponse extends PostResponseDTO{

    private String username;

    public PostDTOFeedResponse(UUID postId,
                               String content,
                               LocalDateTime postDate,
                               List<LikeResponseDTO> likeResponseDTOS,
                               List<ReplyResponseDTO> replyResponseDTO,
                               String username) {
        super(postId, content, postDate, likeResponseDTOS, replyResponseDTO);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
