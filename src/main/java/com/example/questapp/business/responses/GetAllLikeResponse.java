package com.example.questapp.business.responses;

import com.example.questapp.entities.Like;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class GetAllLikeResponse {
	
	Long id;
	Long userId;
	Long postId;
	
	public GetAllLikeResponse(Like entity) {
		this.id = entity.getId();
		this.userId = entity.getUser().getId();
		this.postId = entity.getPost().getId();
	} 
	
}
