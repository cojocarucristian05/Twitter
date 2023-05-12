package com.ligaaclabs.twitter.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"user\"")
@JsonIgnoreProperties({"followers", "following", "posts"})
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
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> followers;

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> following;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Post> posts;
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
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
