package com.example.questapp.business.abstracts;

import java.util.List;

import com.example.questapp.entities.Post;


public interface PostService {
	List<Post> getAll();
	Post getById(Long id);
	void add(Post post);
	void update(Post post);
	void delete(Long id);
}
