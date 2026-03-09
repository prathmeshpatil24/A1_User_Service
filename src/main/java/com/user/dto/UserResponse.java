package com.user.dto;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserResponse {
    private String userId;
    private String userName;
    private String userEmail;
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
