package com.example.questapp.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.business.requests.CreateUserRequest;
import com.example.questapp.dataAccess.abstracts.UserRepository;
import com.example.questapp.entities.User;

import lombok.AllArgsConstructor;

@Service

@AllArgsConstructor

public class UserManager implements UserService {

	private UserRepository userRepository;
	

	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public User getOneUserById(Long id) {

		return userRepository.findById(id).orElseThrow();
	}

	@Override
	public void createOneUser(CreateUserRequest createUserRequest) {
		
		User user = new User();
		user.setId(createUserRequest.getId());
		user.setPassword(createUserRequest.getPassword());
		user.setUserName(createUserRequest.getUserName());
		user.setAvatar(1);
		
		this.userRepository.save(user);

	}

	@Override
	public void updateOneUserById(User user) {

		this.userRepository.save(user);

	}

	@Override
	public void deleteOneUserById(Long id) {

		this.userRepository.deleteById(id);

	}

}
