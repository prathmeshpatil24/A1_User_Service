package com.userService.service;

import com.userService.dto.RatingApiResponse;
import com.userService.dto.RatingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@FeignClient(name = "A3-RATINGSERVICE")
public interface RatingServiceClient {


    @GetMapping("/api/ratings/by-user/{userId}")
    ResponseEntity<RatingApiResponse> getRatingsByUser(@PathVariable String userId);
}
