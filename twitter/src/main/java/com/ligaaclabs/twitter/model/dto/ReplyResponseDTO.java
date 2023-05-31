package com.ligaaclabs.twitter.model.dto;

public class ReplyResponseDTO {
    private String content;
    private String username;

    public ReplyResponseDTO(String content, String username) {
        this.content = content;
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }
}
