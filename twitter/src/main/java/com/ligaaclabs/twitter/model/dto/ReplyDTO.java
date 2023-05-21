package com.ligaaclabs.twitter.model.dto;

import com.ligaaclabs.twitter.model.entities.User;

import java.util.UUID;

public class ReplyDTO {
    private String content;
    private boolean isPublic;
    private UUID postId;
    private UUID userId;

    public ReplyDTO(String content, boolean isPublic, UUID postId, UUID userId) {
        this.content = content;
        this.isPublic = isPublic;
        this.postId = postId;
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public UUID getPostId() {
        return postId;
    }

    public UUID getUserId() {
        return userId;
    }
}
