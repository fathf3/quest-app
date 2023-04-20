package com.example.questapp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.questapp.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

	private Long id;
	private String userName;
	private String password;
	public Collection<? extends GrantedAuthority> authorites;
	
	public JwtUserDetails(Long id, String userName, String password, Collection<? extends GrantedAuthority> authorites) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.authorites = authorites;
	}
	
	public static JwtUserDetails create(User user) {
		
		List<GrantedAuthority> authoritiesList = new ArrayList<>();
		authoritiesList.add(new SimpleGrantedAuthority("user"));
		return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(), authoritiesList);
		
	}
	
	
	
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}



	

}
