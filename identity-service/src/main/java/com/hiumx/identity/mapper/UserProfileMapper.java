package com.hiumx.identity.mapper;

import org.mapstruct.Mapper;

import com.hiumx.identity.dto.request.UserCreationRequest;
import com.hiumx.identity.dto.request.UserProfileCreationRequest;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
