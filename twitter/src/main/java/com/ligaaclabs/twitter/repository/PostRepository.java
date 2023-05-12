package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, UUID> {

//    void createPost(Post post);
//
//    List<Post> getAllPosts();
//
//    public List<Post> getOwnPostsByTimestamp(User user, LocalDateTime timestamps);
//
//    List<Post> getOwnPosts(User user);
//
//    Post getPostById(Integer id);
    @Query("SELECT p FROM Post p WHERE p.user = ?1")
    List<Post> findPostsByUserId(UUID userId);
}
