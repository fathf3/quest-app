package com.example.questapp.business.concretes;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.questapp.dataAccess.abstracts.UserRepository;
import com.example.questapp.entities.User;
import com.example.questapp.security.JwtUserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
	
		
		return JwtUserDetails.create(user);
	}
	
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).get();
		return JwtUserDetails.create(user);
	}
	

}
