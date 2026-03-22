package com.userService.controller;


import com.userService.dto.UserRequest;
import com.userService.dto.UserResponse;
import com.userService.dto.UserWithRatingResponse;
import com.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


//Only core User APIs (CRUD)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create new user
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {

        UserResponse createdUser = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "message","new user is created with:- " +  createdUser.getUserId(),
                        "status", HttpStatus.CREATED,
                        "timestamp", LocalDateTime.now()
                ));
    }

    // Get all users
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of(
                        "data", users,
                        "status", HttpStatus.OK,
                        "timestamp", LocalDateTime.now()
                ));
    }

    // Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {

        //UserResponse user = userService.getUserById(userId);
        UserWithRatingResponse userWithRatings = userService.getUserWithRatings(userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of(
                        "data", userWithRatings,
                        "status", HttpStatus.OK,
                        "timestamp", LocalDateTime.now()
                ));
    }

    // Update user
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable String userId,
            @RequestBody UserRequest request) {

        UserResponse updatedUser = userService.updateUser(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "user is updated for userId:- " + updatedUser.getUserId(),
                        "status", HttpStatus.CREATED,
                        "timestamp", LocalDateTime.now()
                ));
    }


    // Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {

        String message = userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of(
                        "message", message,
                        "status", HttpStatus.OK,
                        "timestamp", LocalDateTime.now()
                ));
    }
}
