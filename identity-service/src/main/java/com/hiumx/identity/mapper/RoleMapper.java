package com.hiumx.identity.mapper;

import com.hiumx.identity.dto.request.RoleRequest;
import com.hiumx.identity.dto.response.RoleResponse;
import com.hiumx.identity.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
