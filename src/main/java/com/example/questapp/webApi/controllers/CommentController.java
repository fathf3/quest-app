package com.example.questapp.webApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questapp.business.abstracts.CommentService;
import com.example.questapp.business.requests.CreateCommentRequest;
import com.example.questapp.business.requests.UpdateCommentRequest;
import com.example.questapp.entities.Comment;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(name = "/comments")
@AllArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	
	@GetMapping
	public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
		return commentService.getAllCommentsWithParam(userId, postId);
		
	}
	
	@GetMapping("/{commentId}")
	public Comment getOneComment(@PathVariable Long commentId) {
		return commentService.getOneCommentById(commentId);
	}
	
	@PostMapping
	public Comment createOneComment(@RequestBody CreateCommentRequest createCommentRequest) {
		return commentService.createOneComment(createCommentRequest);
	}
	
	
	@PutMapping("/{commentId}")
	public Comment updateOneCommentById(@PathVariable Long  commentId, 
			@RequestBody UpdateCommentRequest updateCommentRequest ) {
		
		return commentService.updateOneCommentById(commentId, updateCommentRequest);
		
	}
	
	@DeleteMapping("/{commentId}")
	public void deleteOneCommentById(@PathVariable Long  commentId) {
		
		commentService.deleteOneCommentById(commentId);
		
	}
	
	
	
	
	
	
	
	
	
	
}
