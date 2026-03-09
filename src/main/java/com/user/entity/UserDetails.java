package com.user.entity;

import java.util.ArrayList;
import java.util.List;

import com.user.dto.Rating;

import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    //@Transient
//	public List<Rating> getRatings() {
//		return ratings;
//	}
//
//	public void setRatings(List<Rating> ratings) {
//		this.ratings = ratings;
//	}
//
}
