package com.example.questapp.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.example.questapp.business.requests.CreateCommentRequest;
import com.example.questapp.business.requests.UpdateCommentRequest;
import com.example.questapp.business.responses.GetAllCommentResponse;
import com.example.questapp.entities.Comment;



public interface CommentService {

	List<GetAllCommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId);

	Comment getOneCommentById(Long commentId);

	Comment createOneComment(CreateCommentRequest createCommentRequest);

	Comment updateOneCommentById(Long commentId, UpdateCommentRequest updateCommentRequest);

	void deleteOneCommentById(Long commentId);

	


	
	
	
}
