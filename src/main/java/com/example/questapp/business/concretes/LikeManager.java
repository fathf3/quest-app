package com.example.questapp.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.LikeService;
import com.example.questapp.business.abstracts.PostService;
import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.business.requests.CreateLikeRequest;
import com.example.questapp.business.responses.GetAllLikeResponse;
import com.example.questapp.dataAccess.abstracts.LikeRepository;
import com.example.questapp.entities.Like;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;



@Service

public class LikeManager implements LikeService{
	
	private  LikeRepository likeRepository;
	private  UserService userService;
	private  PostService postService;
	
	
	
	public LikeManager(LikeRepository likeRepository, UserService userService, PostService postService) {
		this.likeRepository = likeRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<GetAllLikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
		List<Like> list;
		if(userId.isPresent() && postId.isPresent()) {
			list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
		}else if(userId.isPresent()) {
			list = likeRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			list = likeRepository.findByPostId(postId.get());
		}else
			list = likeRepository.findAll();
	
		return list.stream().map(like -> new GetAllLikeResponse(like)).collect(Collectors.toList());
		
	}

	public Like getOneLikeById(Long LikeId) {
		return likeRepository.findById(LikeId).orElse(null);
	}

	public Like createOneLike(CreateLikeRequest request) {
		User user = userService.getOneUserById(request.getUserId());
		Post post = postService.getOnePostById(request.getPostId());
		if(user != null && post != null) {
			Like likeToSave = new Like();
			
			likeToSave.setPost(post);
			likeToSave.setUser(user);
			return likeRepository.save(likeToSave);
		}else		
			return null;
	}

	public void deleteOneLikeById(Long likeId) {
		likeRepository.deleteById(likeId);
	}
	
	}


