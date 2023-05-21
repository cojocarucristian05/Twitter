package com.ligaaclabs.twitter.mapper;

import com.ligaaclabs.twitter.model.dto.ReplyDTO;
import com.ligaaclabs.twitter.model.dto.ReplyResponseDTO;
import com.ligaaclabs.twitter.model.entities.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
    ReplyMapper INSTANCE =  Mappers.getMapper(ReplyMapper.class);
    @Mapping(target = "content", source = "content")
//    @Mapping(target = "isPublic", source = "isPublic")
    @Mapping(target = "user.userId", source = "userId")
    @Mapping(target = "parentPost.postId", source = "postId")
    public Reply replayDTOToReplay(ReplyDTO replyDTO);

    @Mapping(target = "content", source = "content")
    @Mapping(target = "username", source = "user.username")
    public ReplyResponseDTO replyToReplyDTOResponse(Reply reply);
}
