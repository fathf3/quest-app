package com.example.questapp.business.abstracts;

import java.util.List;

import com.example.questapp.entities.Comment;



public interface CommentService {
	
	List<Comment> getAll();
	Comment getById(Long id);
	void add(Comment comment);
	void update(Comment comment);
	void delete(Long id);
	
}
