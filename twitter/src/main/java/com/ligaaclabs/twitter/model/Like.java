package com.ligaaclabs.twitter.model;

public class Like {

    private int id;
    private Integer postId;
    private String username;

    public Like(Integer postId, String username) {
        this.postId = postId;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
