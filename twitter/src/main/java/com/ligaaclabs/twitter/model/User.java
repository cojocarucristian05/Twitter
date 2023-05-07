package com.ligaaclabs.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"user\"")
@JsonIgnoreProperties({"followers", "following"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @OneToMany
    private List<User> followers;

    @OneToMany
    private List<User> following;
//
//    @OneToMany
//    private List<Post> posts;
//
//    @OneToMany
//    private List<Like> likes;

    public User() { }
//    public List<Post> getPosts() {
//        return posts;
//    }

    public User(UUID userId, String username, String firstname, String lastname, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        followers = new ArrayList<>();
        following = new ArrayList<>();
//        posts = new ArrayList<>();
//        likes = new ArrayList<>();
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

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowing() {
        return following;
    }

//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }

//    public List<Like> getLikes() {
//        return likes;
//    }
}