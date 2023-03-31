package com.example.questapp.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.dataAccess.abstracts.UserRepository;
import com.example.questapp.entities.User;

import lombok.AllArgsConstructor;

@Service

@AllArgsConstructor

public class UserManager implements UserService{
	
	private UserRepository userRepository;
	
	@Override
	public List<User> getAll() {
		
		return userRepository.findAll();
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(User user) {
		this.userRepository.save(user);
		
	}

	@Override
	public void update(User user) {
		
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
