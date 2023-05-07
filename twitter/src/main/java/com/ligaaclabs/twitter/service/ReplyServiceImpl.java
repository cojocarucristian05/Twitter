package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.Reply;
import com.ligaaclabs.twitter.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public void addReply(Reply reply) {
        replyRepository.save(reply);
    }

    @Override
    public Reply getReplyByParentId(UUID replyParentId) {
        return replyRepository.getReferenceById(replyParentId);
    }

}
