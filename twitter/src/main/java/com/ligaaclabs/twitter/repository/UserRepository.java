package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
    List<User> findByUsernameOrFirstnameOrLastname(String username, String firstname, String lastname);
//    void createUser(User user);
//    boolean findByUsername(User user);
//
//    List<User> getAllUsers();
//
//    boolean search(String query);
//
//    List<User> getSearchUsers(String query);
//    User searchByUsername(String username);
//
//    void addPost(User user, Post post);
}
