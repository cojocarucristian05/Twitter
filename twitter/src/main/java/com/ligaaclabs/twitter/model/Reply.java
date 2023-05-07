package com.ligaaclabs.twitter.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_fk", nullable = false)
    public User user;
    private boolean publicReply;
    @ManyToOne
    @JoinColumn(name = "post_fk", nullable = false)
    private Post parentPost;

    @ManyToOne
    @JoinColumn(name = "parent_reply_fk", nullable = false)
    private Reply parentReply;

    public Reply() {}

    @OneToMany
    List<Reply> replies = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }

    public Reply getParentReply() {
        return parentReply;
    }

    public void setParentReply(Reply parentReply) {
        this.parentReply = parentReply;
    }

    public void setPublicReply(boolean publicReply) {
        this.publicReply = publicReply;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
