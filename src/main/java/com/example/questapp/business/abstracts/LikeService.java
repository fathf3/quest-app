package com.example.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.questapp.business.requests.CreateLikeRequest;
import com.example.questapp.business.responses.GetAllLikeResponse;
import com.example.questapp.entities.Like;

public interface LikeService {

	List<GetAllLikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId);

	Like createOneLike(CreateLikeRequest request);

	Like getOneLikeById(Long likeId);

	void deleteOneLikeById(Long likeId);
	

	
	
}
