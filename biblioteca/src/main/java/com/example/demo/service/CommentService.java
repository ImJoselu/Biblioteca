package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.CommentDTO;
import com.example.demo.model.dto.PostDTO;
import com.example.demo.repository.entity.Comment;

public interface CommentService {

	List<CommentDTO> findAllByPost(PostDTO postDTO);

	void save(CommentDTO comentarioDTO);

	CommentDTO findById(Long idComment);

}
