package com.hiumx.identity.mapper;

import com.hiumx.identity.dto.request.PermissionRequest;
import com.hiumx.identity.dto.response.PermissionResponse;
import com.hiumx.identity.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
