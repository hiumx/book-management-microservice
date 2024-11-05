package com.hiumx.identity.mapper;

import org.mapstruct.Mapper;

import com.hiumx.identity.dto.request.PermissionRequest;
import com.hiumx.identity.dto.response.PermissionResponse;
import com.hiumx.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
