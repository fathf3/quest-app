package com.example.questapp.webApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questapp.business.abstracts.PostService;
import com.example.questapp.business.requests.CreatePostRequest;
import com.example.questapp.business.requests.UpdatePostRequest;
import com.example.questapp.entities.Post;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(name = "/posts")

@AllArgsConstructor
public class PostController {
	
	private final PostService postService;
	
	@GetMapping
	public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
		// @RequestParam -> gelen degerleri Optional<Long> userId'ye atar
		
		return postService.getAllPosts(userId);
	}
	
	
	@GetMapping("/{postId}")
	public Post getOnePost(@PathVariable Long postId) {
		
		return postService.getOnePostById(postId);
		
	}
	
	@PostMapping
	public Post createOnePost(@RequestBody CreatePostRequest createPostRequest) {
		
		return postService.createOnePost(createPostRequest);
		
	}
	
	
	@PutMapping("/{postId}")
	public Post updateOnePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest) {
		
		return postService.updateOnePostById(postId,updatePostRequest);
		
		
	}
	
	@DeleteMapping("/{postId}")
	public void deleteOnePost(@PathVariable Long postId) {
		postService.deleteOnePostById(postId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
