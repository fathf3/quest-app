package com.example.questapp.webApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questapp.business.abstracts.LikeService;
import com.example.questapp.business.requests.CreateLikeRequest;
import com.example.questapp.business.responses.GetAllLikeResponse;
import com.example.questapp.entities.Like;

@RestController
@RequestMapping("/likies")
public class LikeController {
	
	private final LikeService likeService;
	
	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@GetMapping
	public List<GetAllLikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId){
		return likeService.getAllLikesWithParam(userId, postId);
		
	}
	
	@GetMapping("/{likeId}")
	public Like getOneLike(@PathVariable Long likeId) {
		return likeService.getOneLikeById(likeId);
	}
	
	
	@PostMapping
	public Like createOneLike(@RequestBody CreateLikeRequest createLikeRequest) {
		return likeService.createOneLike(createLikeRequest);
	}
	
	@DeleteMapping("/{likeId}")
	public void deleteOneLike(@PathVariable Long likeId) {
		likeService.deleteOneLikeById(likeId);
	}
	
	
	
	
	
	
}
