package com.ligaaclabs.twitter.mapper;

import com.ligaaclabs.twitter.model.dto.ReplyDTO;
import com.ligaaclabs.twitter.model.dto.ReplyResponseDTO;
import com.ligaaclabs.twitter.model.entities.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReplyMapper {

    @Mapping(target = "content", source = "content")
    @Mapping(target = "user.userId", source = "userId")
    @Mapping(target = "parentPost.postId", source = "postId")
    Reply replyDTOToReply(ReplyDTO replyDTO);

    @Mapping(target = "content", source = "content")
    @Mapping(target = "username", source = "user.username")
    ReplyResponseDTO replyToReplyDTOResponse(Reply reply);
}
