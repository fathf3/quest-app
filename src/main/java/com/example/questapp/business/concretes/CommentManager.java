package com.example.questapp.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.CommentService;
import com.example.questapp.business.abstracts.PostService;
import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.business.requests.CreateCommentRequest;
import com.example.questapp.business.requests.UpdateCommentRequest;
import com.example.questapp.dataAccess.abstracts.CommentRepository;
import com.example.questapp.entities.Comment;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor


public class CommentManager implements CommentService {

	private final CommentRepository commentRepository;
	private final UserService userService;
	private final PostService postService;
		
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

	@Override
	public Comment createOneComment(CreateCommentRequest createCommentRequest) {
		User user = userService.getOneUserById(createCommentRequest.getUserId());
		Post post = postService.getOnePostById(createCommentRequest.getPostId());
		if(user != null && post != null) {
			Comment commentToSave = new Comment();
			commentToSave.setId(createCommentRequest.getId());
			commentToSave.setText(createCommentRequest.getText());
			commentToSave.setUser(user);
			commentToSave.setPost(post);
			return commentRepository.save(commentToSave);
		}else
			return null;
	}

	@Override
	public Comment updateOneCommentById(Long commentId, UpdateCommentRequest updateCommentRequest) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()) {
			Comment commentToUpdate = comment.get();
			commentToUpdate.setText(updateCommentRequest.getText());
			
			return commentRepository.save(commentToUpdate);
		}else
			return null;
	}

	@Override
	public void deleteOneCommentById(Long commentId) {
		
		commentRepository.deleteById(commentId);
	}

}
