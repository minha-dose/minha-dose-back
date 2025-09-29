package com.minhadose.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.minhadose.demo.dto.CreateUserDTO;
import com.minhadose.demo.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userId", ignore = true)
    UserModel toUserModel(CreateUserDTO dto);
    CreateUserDTO toCreateUserDTO(UserModel userModel);
}