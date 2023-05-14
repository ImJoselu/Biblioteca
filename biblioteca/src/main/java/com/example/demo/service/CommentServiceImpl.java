package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CommentDTO;
import com.example.demo.model.dto.PostDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.CommentRepository;
import com.example.demo.repository.entity.Comment;
import com.example.demo.repository.entity.Post;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Override
	public List<CommentDTO> findAllByPost(PostDTO postDTO) {

		List<CommentDTO> listaComentariosDTO = new ArrayList<>();
		// Mapa de comentarios
		Map<Long, CommentDTO> comentariosMap = new HashMap<>();

		for (Comment comment : commentRepository.findAllByPost(postDTO.getId())) {

			CommentDTO commentDTO = CommentDTO.convertToDTO(comment, postDTO);

			commentDTO.setListaComentariosDTO(new ArrayList<>());

			if (comment.getComentarioPadre() == null) {
				listaComentariosDTO.add(commentDTO);

			}

			else {
				// Si tiene un comentario padre, añadir a la lista de comentarios del comentario
				// padre correspondiente
				Long comentarioPadreId = comment.getComentarioPadre().getId();
				CommentDTO comentarioPadreDTO = comentariosMap.get(comentarioPadreId);

				if (comentarioPadreDTO != null) {
					comentarioPadreDTO.getListaComentariosDTO().add(commentDTO);
				}
			}

			// Añadir el comentario al mapa de comentarios
			comentariosMap.put(commentDTO.getId(), commentDTO);
		}

		return listaComentariosDTO;
	}

	@Override
	public void save(CommentDTO comentarioDTO) {
		Comment comment = new Comment();
		comment.setContenido(comentarioDTO.getContenidoDTO());
		comment.setFechaCreacion(LocalDateTime.now()); // Agrega esta línea

		Post post = new Post();
		post.setId(comentarioDTO.getPostDTO().getId());
		comment.setPost(post);
		comment.setUsuario(UsuarioDTO.convertToEntity(comentarioDTO.getUsuarioDTO()));
		Comment commentPadre = new Comment();
		if (comentarioDTO.getComentarioPadreDTO() != null) {
			commentPadre.setId(comentarioDTO.getComentarioPadreDTO().getId());
			comment.setComentarioPadre(commentPadre);
		}
		commentRepository.save(comment);
	}

	@Override
	public CommentDTO findById(Long idComment) {
		return CommentDTO.convertToDTO(commentRepository.findById(idComment).get(),
				PostDTO.convertToDTO(commentRepository.findById(idComment).get().getPost()));
	}

}
