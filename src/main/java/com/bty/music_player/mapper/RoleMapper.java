package com.bty.music_player.mapper;

import java.util.HashSet;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.bty.music_player.dto.request.RoleCreationRequest;
import com.bty.music_player.dto.response.RoleResponse;
import com.bty.music_player.entity.Permission;
import com.bty.music_player.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    
    @Mapping(target = "name", source = "name")
    @Mapping(target = "describe", source = "describe")
    @Mapping(target = "permissionNames", source = "permissions", qualifiedByName = "PermissionsToPermissionNames")
    RoleResponse toRoleResponse(Role role);
    
    @Named("PermissionsToPermissionNames")
    public static Set<String> toPermissionNames(Set<Permission> permissions) {
        Set<String> permissionNames = new HashSet<>();
        permissions.forEach(permission -> {
            permissionNames.add(permission.getName());
        });
        return permissionNames;
    }
    
    @Mapping(target = "name", source = "name")
    @Mapping(target = "describe", source = "describe")
    Role toRole(RoleCreationRequest request);
    
}
