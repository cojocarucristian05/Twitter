package com.ligaaclabs.twitter.model;
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
    private UUID id;


    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;

    @OneToMany
    private List<Like> likes;

    @OneToMany
    private List<Reply> replies;

    public LocalDateTime getDate() {
        return date;
    }

    public Post() {}
    public Post(UUID id, String content, LocalDateTime date, User user) {
        this.id = id;
        this.content = content;
        this.date = date;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                "content='" + content + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", likes=" + likes +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
