package com.ligaaclabs.twitter.model.dto;


import com.ligaaclabs.twitter.model.entities.Like;
import com.ligaaclabs.twitter.model.entities.Reply;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PostResponseDTO {

    private UUID postId;
    private String content;
    private LocalDateTime postDate;

    private List<LikeResponseDTO> likeResponseDTOS;
    private List<ReplyResponseDTO> replyResponseDTO;

    public PostResponseDTO(UUID postId,
                           String content,
                           LocalDateTime postDate,
                           List<LikeResponseDTO> likeResponseDTOS,
                           List<ReplyResponseDTO> replyResponseDTO) {
        this.postId = postId;
        this.content = content;
        this.postDate = postDate;
        this.likeResponseDTOS = likeResponseDTOS;
        this.replyResponseDTO = replyResponseDTO;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public List<LikeResponseDTO> getLikesResponseDTO() {
        return likeResponseDTOS;
    }

    public UUID getPostId() {
        return postId;
    }

    public List<ReplyResponseDTO> getReplyDTOResponses() {
        return replyResponseDTO;
    }
}
