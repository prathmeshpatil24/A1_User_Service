package com.userService.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Setter
//@Getter
//@Data
public class UserRequest {

    private String userId;
    private String userName;
    private String userEmail;
    private String about;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
