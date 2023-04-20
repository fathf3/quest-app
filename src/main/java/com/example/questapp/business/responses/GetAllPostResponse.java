package com.example.questapp.business.responses;

import java.util.List;

import com.example.questapp.entities.Like;
import com.example.questapp.entities.Post;

import lombok.Data;

@Data
public class GetAllPostResponse {
	
	private Long id;
	private Long userId;
	private String userName;
	private String text;
	private String title;
	private List<GetAllLikeResponse> postLikes;
	
	
	public GetAllPostResponse(Post post, List<GetAllLikeResponse> likes) {
		
		this.id = post.getId();
		this.userId = post.getUser().getId();
		this.userName = post.getUser().getUserName();
		this.text = post.getText();
		this.title = post.getTitle();
		this.postLikes = likes;
	}


}
