package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.Reply;

import java.util.UUID;

public interface ReplyService {

    void addReply(Reply reply);

    Reply getReplyByParentId(UUID replyParentId);
}
