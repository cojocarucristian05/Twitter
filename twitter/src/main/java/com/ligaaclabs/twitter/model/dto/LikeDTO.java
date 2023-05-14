package com.ligaaclabs.twitter.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LikeDTO {
    private String username;
    private UUID postId;

    public LikeDTO(String username, UUID postId) {
        this.username = username;
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public UUID getPostId() {
        return postId;
    }
}
