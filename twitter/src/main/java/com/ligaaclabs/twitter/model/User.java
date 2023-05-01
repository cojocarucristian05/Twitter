package com.ligaaclabs.twitter.model;

import lombok.ToString;

import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private List<String> followers;
    private List<String> following;

    private List<Post> posts;
    private List<Like> likes;
    public List<Post> getPosts() {
        return posts;
    }

    public User(String username, String firstname, String lastname, String email, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        followers = new ArrayList<>();
        following = new ArrayList<>();
        posts = new ArrayList<>();
        likes = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("username='").append(username).append('\'');
        sb.append("firstname='").append(firstname).append('\'');
        sb.append("lastname='").append(lastname).append('\'');
        sb.append("email='").append(email).append('\'');
        sb.append(", followers=[");
        for (String follower : followers) {
            sb.append(follower).append(", ");
        }
        if (!followers.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        sb.append(", following=[");
        for (String followed : following) {
            sb.append(followed).append(", ");
        }
        if (!following.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Like> getLikes() {
        return likes;
    }
}
