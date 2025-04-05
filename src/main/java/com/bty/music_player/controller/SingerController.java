package com.bty.music_player.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bty.music_player.dto.request.ApiResponse;
import com.bty.music_player.dto.request.SingerCreationRequest;
import com.bty.music_player.dto.response.SingerResponse;
import com.bty.music_player.service.SingerService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/singers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SingerController {
    SingerService singerService;
    
    @GetMapping
    public ApiResponse<List<SingerResponse>> getAll() {
        var result = singerService.getAll();
        return ApiResponse.<List<SingerResponse>>builder()
                .result(result)
                .build();
    }
    
    @PostMapping
    public ApiResponse<SingerResponse> create(@Valid @RequestBody SingerCreationRequest request) {
        var result = singerService.create(request);
        return ApiResponse.<SingerResponse>builder()
                .result(result)
                .build();
    }
}
