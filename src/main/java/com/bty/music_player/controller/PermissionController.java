package com.bty.music_player.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bty.music_player.dto.request.ApiResponse;
import com.bty.music_player.dto.response.PermissionResponse;
import com.bty.music_player.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;
    
    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAll() {
        var result = permissionService.getAll();
        return ApiResponse.<List<PermissionResponse>>builder()
            .result(result)
            .build();
    }
}
