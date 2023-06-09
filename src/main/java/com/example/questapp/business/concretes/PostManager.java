package com.example.questapp.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.LikeService;
import com.example.questapp.business.abstracts.PostService;
import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.business.requests.CreatePostRequest;
import com.example.questapp.business.requests.UpdatePostRequest;
import com.example.questapp.business.responses.GetAllLikeResponse;
import com.example.questapp.business.responses.GetAllPostResponse;
import com.example.questapp.dataAccess.abstracts.PostRepository;
import com.example.questapp.entities.Like;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;

import lombok.AllArgsConstructor;

@Service

public class PostManager implements PostService {

	private final PostRepository postRepository;
	private final UserService userService;
	private  LikeService likeService;

	public PostManager(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}
	
	@Autowired
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@Override
	public List<GetAllPostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> postList;
		if (userId.isPresent()) {
			postList =  postRepository.findByUserId(userId.get());
		}
		postList = postRepository.findAll();
		return postList.stream().map(post -> {
			List<GetAllLikeResponse> likes =  likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(post.getId()));
			return new GetAllPostResponse(post, likes);}).collect(Collectors.toList());
		
	}

	@Override
	public Post getOnePostById(Long postId) {

		return postRepository.findById(postId).orElse(null);
	}

	@Override
	public Post createOnePost(CreatePostRequest createPostRequest) {

		User user = userService.getOneUserById(createPostRequest.getUserId());
		if (user == null)
			return null;

		Post toSave = new Post();
		
		toSave.setText(createPostRequest.getText());
		toSave.setTitle(createPostRequest.getTitle());
		toSave.setUser(user);
		return postRepository.save(toSave);
	}

	@Override
	public Post updateOnePostById(Long postId, UpdatePostRequest updatePostRequest) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(updatePostRequest.getText());
			toUpdate.setTitle(updatePostRequest.getTitle());
			postRepository.save(toUpdate);

		}

		return null;
	}

	@Override
	public void deleteOnePostById(Long postId) {

		postRepository.deleteById(postId);

	}

}
