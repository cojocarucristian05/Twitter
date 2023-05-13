package com.ligaaclabs.twitter.mapper;

import com.ligaaclabs.twitter.model.dto.PostDTO;
import com.ligaaclabs.twitter.model.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE =  Mappers.getMapper(PostMapper.class);

    @Mapping(target = "postId", source = "postId")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "postDate", source = "postDate")
    PostDTO postToPostDTO(Post post);
}
