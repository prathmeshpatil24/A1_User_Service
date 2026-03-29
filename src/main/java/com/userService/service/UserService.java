package com.userService.service;

import com.userService.dto.*;
import com.userService.entity.UserDetails;
import com.userService.exception.ResourceNotFoundException;
import com.userService.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
public class UserService {

	private final UserRepository userRepository;

    private final ModelMapper modelMapper;

	private final RestTemplate restTemplate;

    private final String RATING_SERVICE_URL = "http://localhost:9093/api/ratings";

//    private final String RATING_SERVICE_URL = "http://A3_RATINGSERVICE/api/ratings";

    private final RatingServiceClient ratingServiceClient;

    public UserService(ModelMapper modelMapper, UserRepository userRepository, RestTemplate restTemplate,
                       RatingServiceClient ratingServiceClient
    ) {
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.ratingServiceClient = ratingServiceClient;
    }



    // Get all users
    public List<UserResponse> getAllUsers() {

        List<UserDetails> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found in the system");
        }

        return users.stream()
                .map(
                        user -> {
//                            UserResponse userResponse = new UserResponse();
//                            userResponse.setUserId(user.getUserId());
//                            userResponse.setUserName(user.getUserName());
//                            userResponse.setUserEmail(user.getUserEmail());
//                            userResponse.setAbout(user.getAbout());
//                            return userResponse;

                            // Using ModelMapper to convert UserDetails to UserResponse
                            UserResponse response = modelMapper.map(user, UserResponse.class);
                            return response;
                        }
                ).toList();
    }


    // Get user by ID
    public UserResponse getUserById(String userId) {

        UserDetails userDetails = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        // Convert UserDetails to UserResponse using ModelMapper and direct return
        return modelMapper.map(userDetails, UserResponse.class);
    }

    //user with ratings-service data
    public UserWithRatingResponse getUserWithRatings(String userId) {

        UserResponse userResponse = getUserById(userId);
        //UserDetails userDetails = modelMapper.map(userResponse, UserDetails.class);
       // System.out.println("user details: " + userResponse.getUserId() + ", " + userResponse.getUserName() + ", " + userResponse.getUserEmail());

        // Fetch ratings for the user from Rating Service
//        ResponseEntity<RatingApiResponse> ratingApiResponse = restTemplate.getForEntity(
//                RATING_SERVICE_URL + "/by-user/" + userResponse.getUserId(),
//                RatingApiResponse.class);

        //from feign client
        ResponseEntity<RatingApiResponse> ratingApiResponse = ratingServiceClient.getRatingsByUser(userResponse.getUserId());

        List<RatingResponse> ratingResponses = ratingApiResponse.getBody().getData();

        UserWithRatingResponse userWithRatingResponse = new UserWithRatingResponse();
        userWithRatingResponse.setUserId(userResponse.getUserId());
        userWithRatingResponse.setUserName(userResponse.getUserName());
        userWithRatingResponse.setUserEmail(userResponse.getUserEmail());
        userWithRatingResponse.setAbout(userResponse.getAbout());
        userWithRatingResponse.setRatings(ratingResponses);

        return userWithRatingResponse;

    }


    // Create new user
    public UserResponse createUser(UserRequest request) {

        if (request.getUserName() == null || request.getUserEmail() == null) {

            throw new IllegalArgumentException("User name and email are required");
        }

        String userId = UUID.randomUUID().toString();

        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(userId);
        userDetails.setUserName(request.getUserName());
        userDetails.setUserEmail(request.getUserEmail());
        userDetails.setAbout(request.getAbout());

        UserDetails saveUser = userRepository.save(userDetails);

        // Convert UserDetails to UserResponse using ModelMapper and direct return
        return modelMapper.map(saveUser, UserResponse.class);
    }


    // Update user
    public UserResponse updateUser(String userId, UserRequest request) {

        UserDetails existingUser = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        if (request.getUserName() != null) {
            existingUser.setUserName(request.getUserName());
        }

        if (request.getAbout() != null) {
            existingUser.setAbout(request.getAbout());
        }

        if (request.getUserEmail() != null) {
            existingUser.setUserEmail(request.getUserEmail());
        }

        UserDetails updatedUser = userRepository.save(existingUser);

        // Convert UserDetails to UserResponse using ModelMapper and direct return
        return modelMapper.map(updatedUser, UserResponse.class);
    }


    // Delete user
    public String deleteUser(String userId) {

        UserDetails existingUser = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));
            userRepository.delete(existingUser);

            return "User deleted successfully";
        }
}
