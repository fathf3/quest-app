package com.example.questapp.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class CreateCommentRequest {

	private long id;
	private String text;
	private Long userId;
	private Long postId;
	
	
	
}
