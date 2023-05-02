package com.ligaaclabs.twitter.model;

import java.util.ArrayList;
import java.util.List;

public class Reply {
    private int id;
    private String content;

    public String username;
    private boolean publicReply;
    private int parentPostId;

    private int parentReplyId;

    List<String> replies = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParentPostId() {
        return parentPostId;
    }

    public void setParentPostId(int parentPostId) {
        this.parentPostId = parentPostId;
    }

    public int getParentReplyId() {
        return parentReplyId;
    }

    public void setParentReplyId(int parentReplyId) {
        this.parentReplyId = parentReplyId;
    }

    public void setPublicReply(boolean publicReply) {
        this.publicReply = publicReply;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getReplies() {
        return replies;
    }

    public void setReplies(List<String> replies) {
        this.replies = replies;
    }
}
