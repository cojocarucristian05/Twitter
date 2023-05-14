package com.ligaaclabs.twitter.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LikeDTO {
    private UUID userId;    //user who liked the post
    private UUID postId;

    public LikeDTO(UUID userId, UUID postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getPostId() {
        return postId;
    }
}
