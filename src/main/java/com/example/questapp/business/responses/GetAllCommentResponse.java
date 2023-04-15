package com.example.questapp.business.responses;

import com.example.questapp.entities.Comment;

import lombok.Data;

@Data
public class GetAllCommentResponse {

	
	Long id;
	String text;
	Long userId;
	Long postId;
	
	public GetAllCommentResponse(Comment comment) {
		this.id = comment.getId();
		this.text = comment.getText();
		this.postId = comment.getPost().getId();
		this.userId = comment.getUser().getId();
		
	}
}
