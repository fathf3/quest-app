package com.example.questapp.webApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questapp.business.abstracts.CommentService;
import com.example.questapp.business.requests.CreateCommentRequest;
import com.example.questapp.business.responses.GetAllCommentResponse;
import com.example.questapp.entities.Comment;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController{
	
	private final CommentService commentService;
	
	@GetMapping
	public List<GetAllCommentResponse> getAllComments(@RequestParam Optional<Long> userId, 
			@RequestParam Optional<Long> postId){
		
		return commentService.getAllCommentsWithParam(userId, postId);
		
	}
	
	@GetMapping("/{commentId}")
	public Comment getOneComment(@PathVariable Long commentId) {
		return commentService.getOneCommentById(commentId);
	}
	
	@PostMapping
	public Comment createOnePost(@RequestBody CreateCommentRequest createCommentRequest) {
		return commentService.createOneComment(createCommentRequest);
	}
	
}