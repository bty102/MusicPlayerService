package com.bty.music_player.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bty.music_player.dto.request.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse> handleAppException(AppException exception) {
        ApiResponse apiResponse = ApiResponse.builder()
            .code(exception.getErrorCode().getCode())
            .message(exception.getErrorCode().getMessage())
            .build();
        return ResponseEntity.status(exception.getErrorCode().getHttpStatusCode()).body(apiResponse);
    }
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorCode errorCode = ErrorCode.valueOf(exception.getFieldError().getDefaultMessage());
        ApiResponse apiResponse = ApiResponse.builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .build();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiResponse);
    }
}
