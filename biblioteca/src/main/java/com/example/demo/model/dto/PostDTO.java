package com.example.demo.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.repository.entity.Comment;
import com.example.demo.repository.entity.Post;
import com.example.demo.repository.entity.Usuario;

import lombok.Data;
import lombok.ToString;

@Data
public class PostDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String tituloDTO;
	private String contenidoDTO;
	private LocalDateTime fechaCreacionDTO;
	private int likesDTO;
	private String categoriaDTO;

	@ToString.Exclude
	private List<CommentDTO> listaComentariosDTO = new ArrayList<>();
	@ToString.Exclude
	private UsuarioDTO usuarioDTO;

	public static Post convertToEntity(PostDTO postDTO) {

		Post post = new Post();
		post.setId(postDTO.getId());
		post.setTitulo(postDTO.getTituloDTO());
		post.setContenido(postDTO.getContenidoDTO());
		post.setFechaCreacion(postDTO.getFechaCreacionDTO());
		post.setLikes(postDTO.getLikesDTO());
		post.setCategoria(postDTO.getCategoriaDTO());

		for (CommentDTO comentarioDTO : postDTO.getListaComentariosDTO()) {
			post.getListaComentarios().add(CommentDTO.convertToEntity(comentarioDTO));
		}

		Usuario usuario = UsuarioDTO.convertToEntity(postDTO.getUsuarioDTO());
		post.setUsuario(usuario);

		return post;
	}

	public static PostDTO convertToDTO(Post post) {
		PostDTO postDTO = new PostDTO();
		postDTO.setId(post.getId());
		postDTO.setTituloDTO(post.getTitulo());
		postDTO.setContenidoDTO(post.getContenido());
		postDTO.setFechaCreacionDTO(post.getFechaCreacion());
		postDTO.setLikesDTO(post.getLikes());
		postDTO.setCategoriaDTO(post.getCategoria());
		postDTO.setUsuarioDTO(UsuarioDTO.convertToDTO(post.getUsuario()));

		for (Comment comentario : post.getListaComentarios()) {
			postDTO.getListaComentariosDTO().add(CommentDTO.convertToDTO(comentario, postDTO));
		}

		return postDTO;
	}

}
