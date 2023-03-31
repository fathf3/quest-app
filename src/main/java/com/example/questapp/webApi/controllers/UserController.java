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
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
	private UserService userService;
	
	@GetMapping
	public List<User> getAllUsers(){
		return this.userService.getAll();
	}
	
	@GetMapping("/{userId}")
	public User getOneUser(@PathVariable Long UserId) {
		return userService.getById(UserId);
	}
	
	@PostMapping
	public void add(@RequestBody User user) {
		this.userService.add(user);
	}
	
	@PutMapping("/userId")
	public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
			
			
			return newUser;
			
		
	}
	
	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable  Long userId) {
		userService.delete(userId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
