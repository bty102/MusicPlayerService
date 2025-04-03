package com.bty.music_player.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bty.music_player.dto.request.ApiResponse;
import com.bty.music_player.dto.request.RoleCreationRequest;
import com.bty.music_player.dto.response.RoleResponse;
import com.bty.music_player.service.RoleService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;
    
    @GetMapping
    public ApiResponse<List<RoleResponse>> getAll() {
        var result = roleService.getAll();
        return ApiResponse.<List<RoleResponse>>builder()
            .result(result)
            .build();
    }
    
    @PostMapping
    public ApiResponse<RoleResponse> create(@Valid @RequestBody RoleCreationRequest request) {
        var result = roleService.create(request);
        return ApiResponse.<RoleResponse>builder()
            .result(result)
            .build();
    }
}
