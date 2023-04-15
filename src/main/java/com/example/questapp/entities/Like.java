package com.example.questapp.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "likes")
public class Like {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	
	@Column(name = "text", columnDefinition = "text")
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY) // User objesini direk cekme!
	@JoinColumn(name = "user_id", nullable = false) //null olamaz
	@OnDelete(action = OnDeleteAction.CASCADE) // User silinirse postlarda silinsin
	@JsonIgnore
	User user;
	
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "post_id", nullable = false) 
	@OnDelete(action = OnDeleteAction.CASCADE) 
	@JsonIgnore
	Post post;
	
	
}
