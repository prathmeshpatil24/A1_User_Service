package com.userService.dto;

import lombok.Data;

@Data
public class RatingResponse {

    private String ratingId;

//    private String userId;

    private String hotelId;

    private int rating;

    private String feedback;
}
