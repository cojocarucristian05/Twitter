package com.ligaaclabs.twitter.repository;

import com.ligaaclabs.twitter.model.Like;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LikeRepositoryImpl implements LikeRepository {
    private Map<Integer, Like> LIKES = new HashMap<>();
    private Integer index = 0;

    @Override
    public void createLike(Like like) {
        like.setId(index);
        LIKES.put(index, like);
        index++;
    }

}
