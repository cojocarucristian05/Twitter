package com.ligaaclabs.twitter.mapper;

import com.ligaaclabs.twitter.model.dto.LikeDTO;
import com.ligaaclabs.twitter.model.entities.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "postId", source = "post.postId")
    LikeDTO likeToLikeDTO(Like like);
    Like likeDTOToLike(LikeDTO likeDTO);
}
