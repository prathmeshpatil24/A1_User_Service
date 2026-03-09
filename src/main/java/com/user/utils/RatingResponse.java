package com.user.utils;

import java.util.List;
import com.user.dto.Rating;

public class RatingResponse {
    private String message;
    private int status;
    private String timestamp;
    private List<Rating> data;   // ✅ matches "data" field in JSON
    private String error;

    
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public List<Rating> getData() {
		return data;
	}
	public void setData(List<Rating> data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
    
}
