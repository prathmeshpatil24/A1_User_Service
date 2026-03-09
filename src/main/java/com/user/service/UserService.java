package com.user.service;
import java.util.*;

import com.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.dto.Rating;
import com.user.entity.UserDetails;
import com.user.repo.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	public List<UserDetails> getAllUsers() {
		List<UserDetails>users = userRepository.findAll();
        if (users.isEmpty()) {
			throw new RuntimeException("No user Found");
		}
        return users;
	}

    // give only one user data without ratings
	public UserDetails getUserById(String userId) {
	UserDetails userDetails	= userRepository.findById(userId)
		.orElseThrow(()-> new RuntimeException("user not found with id:-" + userId));
        return userDetails;	
  }


	
	public boolean createUser(UserDetails newUser) {
        if(newUser == null || newUser.getUserName()== null || newUser.getUserEmail()== null) {
       	 return false;

			}

			String generatUniqueUserId = UUID.randomUUID().toString();
			newUser.setUserId(generatUniqueUserId);

       return userRepository.save(newUser)!= null;
	}

	
	public boolean updateUser(String userId, UserDetails user) {
	UserDetails existingUser = userRepository.findById(userId)
		.orElseThrow(()-> new RuntimeException("user not found with id:-" + userId));

		 if (user.getUserName()!=null) {
			existingUser.setUserName(user.getUserName());
		}

		 if (user.getAbout()!=null) {
				existingUser.setAbout(user.getAbout());
		}

		 return userRepository.save(existingUser)!= null;

	}

	
	public void deleteUser(String userId) {
		UserDetails existingUser = userRepository.findById(userId)
				.orElseThrow(()-> new RuntimeException("user not found with id:-" + userId));

		userRepository.delete(existingUser);
	}
	

}
