package com.ligaaclabs.twitter.model.dto;

public class LikeResponseDTO {
    private String username;

    public LikeResponseDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
