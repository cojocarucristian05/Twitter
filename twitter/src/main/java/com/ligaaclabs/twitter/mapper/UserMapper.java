package com.ligaaclabs.twitter.mapper;

import com.ligaaclabs.twitter.model.dto.UserDTO;
import com.ligaaclabs.twitter.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "lastname", source = "lastname")
    UserDTO userToUserDTO(User user);
}
