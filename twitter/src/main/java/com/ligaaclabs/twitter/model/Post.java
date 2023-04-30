package com.ligaaclabs.twitter.model;
import java.time.LocalDateTime;

public class Post {

    private String content;
    private LocalDateTime date;
    private String username;

    public LocalDateTime getDate() {
        return date;
    }

    public Post(String content, LocalDateTime date, String username) {
        this.content = content;
        this.date = date;
        this.username = username;
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
                "content='" + content + '\'' +
                ", date=" + date +
                ", user=" + username +
                '}';
    }
}
