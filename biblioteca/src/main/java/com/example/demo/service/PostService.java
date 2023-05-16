package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.LibroDTO;
import com.example.demo.model.dto.PostDTO;

public interface PostService {

	List<PostDTO> findAll();

	PostDTO findById(Long idPost);

	void save(PostDTO postDTO);

}
