package com.hiumx.identity.mapper;

import com.hiumx.identity.dto.request.UserCreationRequest;
import com.hiumx.identity.dto.request.UserProfileCreationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
