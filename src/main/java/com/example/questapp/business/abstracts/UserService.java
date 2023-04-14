package com.example.questapp.business.abstracts;

import java.util.List;

import com.example.questapp.business.requests.CreateUserRequest;
import com.example.questapp.entities.User;

public interface UserService {
	List<User> getAllUsers();
	User getOneUserById(Long id);
	void createOneUser(CreateUserRequest createUserRequest);
	void updateOneUserById(User user);
	void deleteOneUserById(Long id);
	
}
