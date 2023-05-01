package com.ligaaclabs.twitter.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private int id;
    private String content;
    private LocalDateTime date;
    private String username;

    private List<Like> likes;

    public LocalDateTime getDate() {
        return date;
    }

    public Post(int id, String content, LocalDateTime date, String username) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.username = username;
        likes = new ArrayList<>();
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                "content='" + content + '\'' +
                ", date=" + date +
                ", user=" + username +
                ", likes=" + likes +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
