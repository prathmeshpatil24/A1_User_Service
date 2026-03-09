package com.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse<T> {

	 private String message;
	    private int status;
	    private LocalDateTime timestamp;
	    private T data;       // Can be any type:- list, object, etc
	    private Object error; // Can be String or object for error details
}
