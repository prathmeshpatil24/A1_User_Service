package com.user.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.user.dto.ApiResponse;

public class ApiResponseUtil {

	public static <T> ResponseEntity<ApiResponse<T>> success(HttpStatus httpStatus, String message, T data) {
        return ResponseEntity
                .status(httpStatus)
                .body(new ApiResponse<>(message, httpStatus.value(), data, null));
    }

    public static ResponseEntity<ApiResponse<Object>> error(HttpStatus httpStatus, String message, Object errorDetails) {
        return ResponseEntity
                .status(httpStatus)
                .body(new ApiResponse<>(message, httpStatus.value(), null, errorDetails));
    }
}
