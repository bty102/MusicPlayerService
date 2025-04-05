package com.bty.music_player.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.bty.music_player.dto.request.PermissionCreationRequest;
import com.bty.music_player.dto.response.PermissionResponse;
import com.bty.music_player.entity.Permission;
import com.bty.music_player.exception.AppException;
import com.bty.music_player.exception.ErrorCode;
import com.bty.music_player.mapper.PermissionMapper;
import com.bty.music_player.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;
    
    @PreAuthorize("hasRole('ADMIN')")
    public List<PermissionResponse> getAll() {
        return permissionRepository.findAll().stream().map(permissionMapper::toPermissionResponse).toList();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    public PermissionResponse create(PermissionCreationRequest request) {
        if(permissionRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.PERMISSION_EXISTED);
        }

        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }
}
