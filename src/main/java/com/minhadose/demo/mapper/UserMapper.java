package com.minhadose.demo.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.minhadose.demo.dto.request.UserRequest;
import com.minhadose.demo.dto.response.UserResponse;
import com.minhadose.demo.model.UserModel;

@Mapper(componentModel = "spring", uses = AddressMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    @Mapping(target = "userId", ignore = true)
    UserModel toUserModel(UserRequest dto);

    UserResponse toCreateUserDTO(UserModel userModel);
}