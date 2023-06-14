package com.ligaaclabs.twitter.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PostDTO {

    private UUID userId;
    private UUID postId;
    private String content;
    private LocalDateTime postDate;

    public PostDTO(UUID postId, String content, LocalDateTime postDate) {
        this.postId = postId;
        this.content = content;
        this.postDate = postDate;
    }
}
