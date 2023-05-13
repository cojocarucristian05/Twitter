package com.ligaaclabs.twitter.model.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_id", nullable = false)
    private UUID postId;


    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "date", nullable = false)
    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    private List<Like> likes;

    @OneToMany
    private List<Reply> replies;


    public Post() {}
    public Post(UUID postId, String content, LocalDateTime postDate, User user) {
        this.postId = postId;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
        likes = new ArrayList<>();
        replies = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public UUID getId() {
        return postId;
    }

    public void setId(UUID postId) {
        this.postId = postId;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public String getContent() {
        return content;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + postId +
                "content='" + content + '\'' +
                ", date=" + postDate +
                ", user=" + user +
                ", likes=" + likes +
                '}';
    }
}
