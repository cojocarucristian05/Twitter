package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.Reply;

public interface ReplyRepository {

    void createReply(Reply reply);

    Reply getReplyByParentId(Integer id);
}
