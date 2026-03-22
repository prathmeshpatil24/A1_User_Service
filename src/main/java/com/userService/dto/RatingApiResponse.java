package com.userService.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RatingApiResponse {

    private List<RatingResponse> data;
    private String status;
    private LocalDateTime timestamp;

}
