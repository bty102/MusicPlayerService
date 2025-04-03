package com.bty.music_player.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bty.music_player.dto.response.PermissionResponse;
import com.bty.music_player.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    
    @Mapping(target = "name", source = "name")
    @Mapping(target = "describe", source = "describe")
    PermissionResponse toPermissionResponse(Permission permission);
}
