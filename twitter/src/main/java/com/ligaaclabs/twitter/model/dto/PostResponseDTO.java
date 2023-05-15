package com.ligaaclabs.twitter.model.dto;

import com.ligaaclabs.twitter.model.entities.Like;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PostResponseDTO {

    private UUID postId;
    private String content;
    private LocalDateTime postDate;
    private List<LikeResponseDTO> likesResponseDTO;

    public PostResponseDTO(UUID postId, String content, LocalDateTime postDate, List<LikeResponseDTO> likesResponseDTO) {
        this.postId = postId;
        this.content = content;
        this.postDate = postDate;
        this.likesResponseDTO = likesResponseDTO;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public List<LikeResponseDTO> getLikesResponseDTO() {
        return likesResponseDTO;
    }
}
