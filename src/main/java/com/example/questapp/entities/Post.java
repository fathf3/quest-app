package com.example.questapp.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {
	
	
	private long id;
	
	@Column(name = "title")
	private String title;
	
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY) // User objesini direk cekme!
	@JoinColumn(name = "user_id", nullable = false) //null olamaz
	@OnDelete(action = OnDeleteAction.CASCADE) // User silinirse postlarda silinsin
	@JsonIgnore
	private User user;
	
}
