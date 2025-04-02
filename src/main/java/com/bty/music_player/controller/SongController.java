package com.bty.music_player.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bty.music_player.dto.request.ApiResponse;
import com.bty.music_player.dto.request.SongCreationRequest;
import com.bty.music_player.dto.response.SongResponse;
import com.bty.music_player.service.SongService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/songs")
public class SongController {
    SongService songService;
    
    @GetMapping
    public ApiResponse<List<SongResponse>> getAll() {
        var result = songService.getAll();
        return ApiResponse.<List<SongResponse>>builder()
            .result(result)
            .build();
    }
    
    @PostMapping
    public ApiResponse<SongResponse> create(@Valid @RequestBody SongCreationRequest request) {
        var result = songService.create(request);
        return ApiResponse.<SongResponse>builder()
            .result(result)
            .build();
    }
}
