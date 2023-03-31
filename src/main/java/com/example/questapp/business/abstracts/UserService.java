package com.example.questapp.business.abstracts;

import java.util.List;


import com.example.questapp.entities.User;

public interface UserService {
	List<User> getAll();
	User getById(Long id);
	void add(User user);
	void update(User user);
	void delete(Long id);
}
