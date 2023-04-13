package com.example.questapp.business.requests;

import lombok.Data;

@Data
public class CreatePostRequest {
	
	Long id;
	String text;
	String title;
	Long userId;
	
}
