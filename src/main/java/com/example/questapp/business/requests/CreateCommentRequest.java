package com.example.questapp.business.requests;


import lombok.Data;


@Data

public class CreateCommentRequest {

	private long id;
	private String text;
	private Long userId;
	private Long postId;
	
	
	
}
