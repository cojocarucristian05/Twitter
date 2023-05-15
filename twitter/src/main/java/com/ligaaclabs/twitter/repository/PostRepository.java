package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("SELECT p FROM Post p JOIN FETCH p.user u WHERE u.userId = :userId")
    List<Post> findAllByUserUserId(@Param("userId") UUID userId);

//    List<Post> findAllPosts();
}
