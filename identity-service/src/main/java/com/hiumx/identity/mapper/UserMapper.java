package com.hiumx.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.hiumx.identity.dto.request.UserCreationRequest;
import com.hiumx.identity.dto.request.UserUpdateRequest;
import com.hiumx.identity.dto.response.UserResponse;
import com.hiumx.identity.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
