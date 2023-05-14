package com.ligaaclabs.twitter.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"user\"")
@JsonIgnoreProperties({"followers", "following", "posts", "likes"})
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

    @OneToMany(fetch = FetchType.EAGER)
    private List<Like> likes;

    public User() { }

    public User(UUID userId, String username, String firstname, String lastname, String email, String password) {
        this.userId = userId;
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

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Like> getLikes() {
        return likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, firstname, lastname, email, password, followers, following, posts, likes);
    }
}
