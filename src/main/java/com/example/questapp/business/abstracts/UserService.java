package com.example.questapp.business.abstracts;

import java.util.List;

import com.example.questapp.business.requests.CreateUserRequest;
import com.example.questapp.entities.User;

public interface UserService {
	List<User> getAll();
	User getById(Long id);
	void add(CreateUserRequest createUserRequest);
	void update(User user);
	void delete(Long id);
	
}
