package com.example.questapp.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.business.requests.CreateUserRequest;
import com.example.questapp.core.utilities.mappers.ModelMapperService;
import com.example.questapp.dataAccess.abstracts.UserRepository;
import com.example.questapp.entities.User;

import lombok.AllArgsConstructor;

@Service

@AllArgsConstructor

public class UserManager implements UserService {

	private UserRepository userRepository;
	private ModelMapperService modelMapperService;

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
		
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
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
