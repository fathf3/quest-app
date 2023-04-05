package com.example.questapp.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.entities.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")

@AllArgsConstructor

public class UserController {
	private UserService userService;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAll();
	}
	
	@GetMapping("/{id}")
	public User getOneUser(@PathVariable Long id) {
		return userService.getById(id);
	}
	
	@PostMapping
	public void addUser(@RequestBody User user) {
		userService.add(user);
	}
	
	@PutMapping()
	public void updatUser(@RequestBody User newUser) {
			
			userService.update(newUser);
		
	}
	
	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable  Long userId) {
		userService.delete(userId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
