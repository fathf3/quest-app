package com.example.questapp.business.abstracts;

import java.util.List;

import com.example.questapp.entities.Like;



public interface LikeService {
	
	List<Like> getAll();
	Like getById(Long id);
	void add(Like like);
	void update(Like like);
	void delete(Long id);
	
	
}
