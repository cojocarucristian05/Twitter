package com.ligaaclabs.twitter.model;

import java.time.LocalDate;

public class Post {

    private String content;
    private LocalDate date;
    private String username;

    public Post(String content, LocalDate date, String username) {
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
