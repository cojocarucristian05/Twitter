package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface ReplyRepository extends JpaRepository<Reply, UUID> {

//    void createReply(Reply reply);
//
//    Reply getReplyByParentId(Integer id);
}
