package com.example.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.questapp.business.requests.CreatePostRequest;
import com.example.questapp.business.requests.UpdatePostRequest;
import com.example.questapp.business.responses.GetAllPostResponse;
import com.example.questapp.entities.Post;

public interface PostService {

	List<GetAllPostResponse> getAllPosts(Optional<Long> userId);

	Post getOnePostById(Long postId);

	Post createOnePost(CreatePostRequest createPostRequest);

	Post updateOnePostById(Long postId, UpdatePostRequest updatePostRequest);

	void deleteOnePostById(Long postId);
	
	
}
