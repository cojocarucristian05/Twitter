package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface LikeRepository extends JpaRepository<Like, UUID> {
}
