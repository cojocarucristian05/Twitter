package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.Reply;
import com.ligaaclabs.twitter.repository.ReplyRepository;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public void addReply(Reply reply) {
        replyRepository.createReply(reply);
    }

    @Override
    public Reply getReplyByParentId(Integer replyParentId) {
        return replyRepository.getReplyByParentId(replyParentId);
    }

}
