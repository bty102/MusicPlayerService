package com.bty.music_player.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bty.music_player.dto.request.AccountCreationRequest;
import com.bty.music_player.dto.request.AccountUpdateRequest;
import com.bty.music_player.dto.request.ApiResponse;
import com.bty.music_player.dto.response.AccountResponse;
import com.bty.music_player.service.AccountService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/accounts")
public class AccountController {
    AccountService accountService;
    
    @GetMapping
    public ApiResponse<List<AccountResponse>> getAll() {
        var result = accountService.getAll();
        return ApiResponse.<List<AccountResponse>>builder()
            .result(result)
            .build();
    }
    
    @PostMapping
    public ApiResponse<AccountResponse> create(@Valid @RequestBody AccountCreationRequest request) {
        var result = accountService.create(request);
        return ApiResponse.<AccountResponse>builder()
            .result(result)
            .build();
    }
    
    @PutMapping("/{id}")
    public ApiResponse<AccountResponse> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody AccountUpdateRequest request) {
        var result = accountService.update(id, request);
        return ApiResponse.<AccountResponse>builder()
            .result(result)
            .build();
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable(name = "id") Integer id) {
        accountService.delete(id);
        return ApiResponse.<String>builder()
            .result("account deleted")
            .build();
    }
    
    @GetMapping("myinfo")
    public ApiResponse<AccountResponse> getMyInfo() {
        var result = accountService.getMyInfo();
        return ApiResponse.<AccountResponse>builder()
            .result(result)
            .build();
    }
}
