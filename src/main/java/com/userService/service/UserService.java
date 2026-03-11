package com.userService.service;

import com.userService.dto.UserRequest;
import com.userService.dto.UserResponse;
import com.userService.entity.UserDetails;
import com.userService.exception.ResourceNotFoundException;
import com.userService.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

//	@Autowired
//	private RestTemplate restTemplate;

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
