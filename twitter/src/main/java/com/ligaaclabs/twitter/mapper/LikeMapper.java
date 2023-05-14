package com.ligaaclabs.twitter.mapper;

import com.ligaaclabs.twitter.model.dto.LikeDTO;
import com.ligaaclabs.twitter.model.entities.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "postId", source = "post.postId")
    LikeDTO likeToLikeDTO(Like like);
}
