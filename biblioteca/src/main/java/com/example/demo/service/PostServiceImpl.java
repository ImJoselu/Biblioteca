package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CommentDTO;
import com.example.demo.model.dto.LibroDTO;
import com.example.demo.model.dto.PostDTO;
import com.example.demo.repository.dao.LibroRepository;
import com.example.demo.repository.dao.PostRepository;
import com.example.demo.repository.entity.Comment;
import com.example.demo.repository.entity.Libro;
import com.example.demo.repository.entity.Post;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Override
	public List<PostDTO> findAll() {
		List<PostDTO> listaDTONueva = new ArrayList<>();

		for (Post post : postRepository.findAll()) {
			listaDTONueva.add(PostDTO.convertToDTO(post));
		}

		return listaDTONueva;
	}

	@Override
	public PostDTO findById(Long idPost) {
		// TODO Auto-generated method stub
		return PostDTO.convertToDTO(postRepository.findById(idPost).get());
	}

	@Override
	public void save(PostDTO postDTO) {

		Post post = PostDTO.convertToEntity(postDTO);
		if (post.getFechaCreacion() == null) {
			post.setFechaCreacion(LocalDateTime.now()); // Agrega esta línea
		}

		postRepository.save(post);
	}

}
