package com.ligaaclabs.twitter.model.dto;

import com.ligaaclabs.twitter.model.entities.Like;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDTO {
    private String content;
    private LocalDateTime postDate;
    private List<Like> likes;

    public PostResponseDTO(String content, LocalDateTime postDate, List<Like> likes) {
        this.content = content;
        this.postDate = postDate;
        this.likes = likes;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public List<Like> getLikes() {
        return likes;
    }
}
