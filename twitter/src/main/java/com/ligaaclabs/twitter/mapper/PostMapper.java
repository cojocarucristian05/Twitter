package com.ligaaclabs.twitter.mapper;

import com.ligaaclabs.twitter.model.dto.LikeResponseDTO;
import com.ligaaclabs.twitter.model.dto.PostDTO;
import com.ligaaclabs.twitter.model.dto.PostResponseDTO;
import com.ligaaclabs.twitter.model.dto.ReplyResponseDTO;
import com.ligaaclabs.twitter.model.entities.Like;
import com.ligaaclabs.twitter.model.entities.Post;
import com.ligaaclabs.twitter.model.entities.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {
    LikeMapper LIKE_MAPPER = Mappers.getMapper(LikeMapper.class);
    ReplyMapper REPLY_MAPPER = Mappers.getMapper(ReplyMapper.class);

    @Mapping(target = "userId", source = "post.user.userId")
    @Mapping(target = "postId", source = "postId")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "postDate", source = "postDate")
    PostDTO postToPostDTO(Post post);

    @Named("toLikeMapper")
    default LikeResponseDTO toLikeMapper(Like like) {
        return LIKE_MAPPER.likeToLikeResponseDTO(like);
    }

    @Named("toReplyMapper")
    default ReplyResponseDTO toReplyMapper(Reply reply) {
        return REPLY_MAPPER.replyToReplyDTOResponse(reply);
    }

    @Mapping(target = "postId", source = "postId")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "postDate", source = "postDate")
    @Mapping(target = "likeResponseDTOS", source = "likes", qualifiedByName = "toLikeMapper")
    @Mapping(target = "replyResponseDTO", source = "replies", qualifiedByName = "toReplyMapper")
    PostResponseDTO postToPostResponseDTO(Post post);
}
