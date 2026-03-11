package com.userService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "um_user_details")
public class UserDetails {
	
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;
	private String userName;
	private String userEmail;
	private String about;
	
	/*@Transient is used to tell JPA to ignore a field when saving or loading data from the databa*/

//    @Transient
//	public List<Rating> getRatings() {
//		return ratings;
//	}
//
//	public void setRatings(List<Rating> ratings) {
//		this.ratings = ratings;
//	}


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
