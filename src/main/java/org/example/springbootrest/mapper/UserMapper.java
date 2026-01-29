package org.example.springbootrest.mapper;

import org.example.springbootrest.dto.UserDTO;
import org.example.springbootrest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
