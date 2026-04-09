package com.userService.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserWithRatingResponse {

    private String userId;
    private String userName;
    private String userEmail;
    private String about;
    private List<RatingResponse> ratings = new ArrayList<>();
    private boolean ratingServiceAvailable;
}
