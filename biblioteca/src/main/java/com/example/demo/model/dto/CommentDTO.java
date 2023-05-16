package com.example.demo.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.repository.entity.Comment;
import com.example.demo.repository.entity.Post;
import com.example.demo.repository.entity.Usuario;

import lombok.Data;
import lombok.ToString;

@Data
public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 2L;
	private Long id;

	private String contenidoDTO;

	private LocalDateTime fechaCreacionDTO;

	@ToString.Exclude
	private UsuarioDTO usuarioDTO;

	@ToString.Exclude
	private PostDTO postDTO;

	@ToString.Exclude
	private CommentDTO comentarioPadreDTO;

	@ToString.Exclude
	private List<CommentDTO> listaComentariosDTO = new ArrayList<>();

	// private boolean esPadre;

	public static CommentDTO convertToDTO(Comment comment, PostDTO postDTO) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(comment.getId());
		commentDTO.setContenidoDTO(comment.getContenido());
		// commentDTO.setEsPadre(comment.isEsPadre());
		commentDTO.setFechaCreacionDTO(comment.getFechaCreacion());
		commentDTO.setUsuarioDTO(UsuarioDTO.convertToDTO(comment.getUsuario()));

		commentDTO.setPostDTO(postDTO);

		if (comment.getComentarioPadre() != null) {
			commentDTO.setComentarioPadreDTO(convertToDTO(comment.getComentarioPadre(), postDTO));
		} /*
			 * comment.getListaComentarios().forEach(commentHijo ->
			 * commentDTO.getListaComentariosDTO().add(convertToDTO(commentHijo)));
			 */

		return commentDTO;
	}

	public static Comment convertToEntity(CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setId(commentDTO.getId());
		comment.setContenido(commentDTO.getContenidoDTO());
		// comment.setEsPadre(commentDTO.isEsPadre());
		comment.setFechaCreacion(commentDTO.getFechaCreacionDTO());
		comment.setUsuario(UsuarioDTO.convertToEntity(commentDTO.getUsuarioDTO()));
		Post post = new Post();
		post.setId(commentDTO.getPostDTO().getId());
		comment.setPost(post);

		if (commentDTO.getComentarioPadreDTO() != null) {
			comment.setComentarioPadre(convertToEntity(commentDTO.getComentarioPadreDTO()));
		} /*
			 * commentDTO.getListaComentariosDTO() .forEach(commentHijoDTO ->
			 * comment.getListaComentarios().add(convertToEntity(commentHijoDTO)));
			 */

		return comment;
	}

}
