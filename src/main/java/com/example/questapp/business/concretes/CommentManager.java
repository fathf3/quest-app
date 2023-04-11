package com.example.questapp.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.CommentService;
import com.example.questapp.dataAccess.abstracts.CommentRepository;
import com.example.questapp.entities.Comment;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class CommentManager implements CommentService {

	private CommentRepository commentRepository;
		
	@Override
	public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
		
		if(userId.isPresent() && postId.isPresent()) 
			return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
		
		else if(userId.isPresent()) 
			return commentRepository.findByUserId(userId.get());
		
		else if(postId.isPresent()) 
			return commentRepository.findByPostId(postId.get());
		
		else
			return commentRepository.findAll();
		
	}

	@Override
	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

}
