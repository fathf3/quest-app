package com.example.questapp.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtTokenProvider {
	
	
	@Value("${quest.app.secret}")
	private String APP_SECRET;
	
	@Value("${quest.expires.in}")
	private Long EXPIRES_IN; //Token suresi
	
	public String generateJwtToken(Authentication auth) {
		
		JwtUserDetails jwtUserDetails = (JwtUserDetails) auth.getPrincipal();
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
		return Jwts.builder().setSubject((Long.toString(jwtUserDetails.getId())))
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
		
	}
	
	Long getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
			return !isTokenExpired(token);
		}catch (SignatureException e){
			return false;
		}catch(MalformedJwtException e) {
			return false;
		}catch(ExpiredJwtException e) {
			return false;
		}catch(UnsupportedJwtException e) {
			return false;
		}catch(IllegalArgumentException e) {
			return false;
		}
		
	}

	private boolean isTokenExpired(String token) {
		
		Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
		
		return expiration.before(new Date());
	}
	
	
}
