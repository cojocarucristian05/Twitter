package com.ligaaclabs.twitter.service;

import com.ligaaclabs.twitter.model.entities.Like;
import com.ligaaclabs.twitter.repository.LikeRepository;

import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }


    @Override
    public void createLike(Like like) {
        likeRepository.save(like);
    }
}
