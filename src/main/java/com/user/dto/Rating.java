package com.user.dto;


public class Rating {
	
	private String ratingId;  // UUID string
//    private String userId;    // UUID string
    private String hotelId;   // UUID string
    private int rating;       // numeric
    private String feedback;  // text
//    private Hotel hotel; //Associated hotel details
	
   
//    public Rating() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Rating(String ratingId, String userId, String hotelId, int rating, String feedback ) {
//		super();
//		this.ratingId = ratingId;
//		this.userId = userId;
//		this.hotelId = hotelId;
//		this.rating = rating;
//		this.feedback = feedback;
////		this.hotel = hotel;
//	}

	public String getRatingId() {
		return ratingId;
	}

	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}

//	public String getUserId() {
//		return userId;
//	}

//	public void setUserId(String userId) {
//		this.userId = userId;
//	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	
	
}
