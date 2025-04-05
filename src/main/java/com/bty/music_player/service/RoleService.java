package com.bty.music_player.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.bty.music_player.dto.request.RoleCreationRequest;
import com.bty.music_player.dto.response.RoleResponse;
import com.bty.music_player.entity.Permission;
import com.bty.music_player.entity.Role;
import com.bty.music_player.exception.AppException;
import com.bty.music_player.exception.ErrorCode;
import com.bty.music_player.mapper.RoleMapper;
import com.bty.music_player.repository.PermissionRepository;
import com.bty.music_player.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }
    
    public RoleResponse create(RoleCreationRequest request) {
        if(roleRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.ROLE_EXISTED);
        }
        
        Role role = roleMapper.toRole(request);
        Set<Permission> permissions = new HashSet<Permission>();
        if(request.getPermissionNames() == null) {
            role.setPermissions(permissions);
        } else {
            request.getPermissionNames().forEach(permissionNames -> {
                if(permissionRepository.existsByName(permissionNames)) {
                    Permission permission = permissionRepository.findByName(permissionNames)
                        .orElseThrow();
                    permissions.add(permission);
                }
            });
            role.setPermissions(permissions);
        }
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }
}
