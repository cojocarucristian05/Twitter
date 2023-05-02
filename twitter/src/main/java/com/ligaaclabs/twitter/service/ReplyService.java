package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.Reply;

public interface ReplyService {

    void addReply(Reply reply);

    Reply getReplyByParentId(Integer replyParentId);
}
