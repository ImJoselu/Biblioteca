package com.example.demo.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	// Otros atributos y métodos getter/setter

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private List<Comment> replies = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Comment parent;
}