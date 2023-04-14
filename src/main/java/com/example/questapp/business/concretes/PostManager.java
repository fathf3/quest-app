package com.example.questapp.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.questapp.business.abstracts.PostService;
import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.business.requests.CreatePostRequest;
import com.example.questapp.business.requests.UpdatePostRequest;
import com.example.questapp.dataAccess.abstracts.PostRepository;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostManager implements PostService {

	private final PostRepository postRepository;
	private final UserService userService;

	@Override
	public List<Post> getAllPosts(Optional<Long> userId) {

		if (userId.isPresent()) {
			return postRepository.findByUserId(userId.get());
		}

		return postRepository.findAll();
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
		toSave.setId(createPostRequest.getId());
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
