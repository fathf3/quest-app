package com.example.questapp.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

	private String text;
	private Long userId;
	private Long postId;
	
	
	
}
