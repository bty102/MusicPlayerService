package com.bty.music_player.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bty.music_player.dto.request.ApiResponse;
import com.bty.music_player.dto.request.SongOfSingerCreationRequest;
import com.bty.music_player.dto.request.SongOfSingerDeleteRequest;
import com.bty.music_player.dto.request.SongOfSingerUpdateRequest;
import com.bty.music_player.dto.response.SongOfSingerResponse;
import com.bty.music_player.service.SongOfSingerService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/songofsingers")
public class SongOfSingerController {

    SongOfSingerService songOfSingerService;
    
    @GetMapping
    public ApiResponse<List<SongOfSingerResponse>> getAll() {
        var result = songOfSingerService.getAll();
        return ApiResponse.<List<SongOfSingerResponse>>builder()
                .result(result)
                .build();
    }
    
    @PostMapping
    public ApiResponse<SongOfSingerResponse> create(@Valid @RequestBody SongOfSingerCreationRequest request) {
        var result = songOfSingerService.create(request);
        return ApiResponse.<SongOfSingerResponse>builder()
            .result(result)
            .build();
    }
    
    @PutMapping
    public ApiResponse<SongOfSingerResponse> updateFt(@Valid @RequestBody SongOfSingerUpdateRequest request) {
        var result = songOfSingerService.updateFt(request);
        return ApiResponse.<SongOfSingerResponse>builder()
            .result(result)
            .build();
    }
    
    @DeleteMapping
    public ApiResponse<String> delete(@RequestBody SongOfSingerDeleteRequest request) {
        songOfSingerService.delete(request);
        return ApiResponse.<String>builder()
            .result("deleted")
            .build();
    }
}
