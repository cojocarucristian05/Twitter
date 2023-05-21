package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.dto.UserDTO;
import com.ligaaclabs.twitter.model.entities.Post;
import com.ligaaclabs.twitter.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> searchByUsernameOrLastnameOrFirstname(String username, String lastname, String firstname);
    Optional<User> findUserByUsername(String username);

    Page<User> findUsersByUsernameOrLastnameOrFirstname(String username, String lastname, String firstname, Pageable pageable);

    void deleteUserByUserId(UUID userId);
}
