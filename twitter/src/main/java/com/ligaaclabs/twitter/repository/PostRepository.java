package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByUserId(UUID userId);
//    void createPost(Post post);
//
//    List<Post> getAllPosts();
//
//    public List<Post> getOwnPostsByTimestamp(User user, LocalDateTime timestamps);
//
//    List<Post> getOwnPosts(User user);
//
//    Post getPostById(Integer id);

}
