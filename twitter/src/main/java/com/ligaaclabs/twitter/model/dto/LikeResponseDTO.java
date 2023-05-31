package com.ligaaclabs.twitter.model.dto;

import java.util.UUID;

public class LikeResponseDTO {
    private String username;

    public LikeResponseDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
