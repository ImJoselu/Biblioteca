package com.example.demo.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.LibroDTO;
import com.example.demo.model.dto.PostDTO;
import com.example.demo.model.dto.CommentDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.entity.Comment;
import com.example.demo.service.EjemplarService;
import com.example.demo.service.PostService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UsuarioService usuarioService;

	private static final Logger log = LoggerFactory.getLogger(CommentController.class);

	@PostMapping("/foro/{idPost}/comentar/{idComment}/{usernameUsuario}")
	public ModelAndView comentarPost(@PathVariable Long idComment, @PathVariable Long idPost,
			@ModelAttribute("nuevoComentarioDTO") CommentDTO comentarioDTO, @PathVariable("usernameUsuario") String usernameUsuario) {
		log.info("PostController - comentarPost: Se intenta crear un comentario en el post " + idPost);

		ModelAndView mav = new ModelAndView("");

		UsuarioDTO usuarioDTO = usuarioService.findByName(usernameUsuario); // Cambiar por el ID del usuario logeado en el momento

		comentarioDTO.setUsuarioDTO(usuarioDTO);

		PostDTO postDTO = postService.findById(idPost);
		CommentDTO commentPadreDTO = commentService.findById(idComment);

		if (postDTO != null) {
			comentarioDTO.setPostDTO(postDTO);

			comentarioDTO.setComentarioPadreDTO(commentPadreDTO);
			if (commentPadreDTO.getListaComentariosDTO() == null) {

				commentPadreDTO.setListaComentariosDTO(new ArrayList<CommentDTO>());
			}
			commentPadreDTO.getListaComentariosDTO().add(comentarioDTO);

			commentService.save(comentarioDTO);

			mav.setViewName("redirect:/foro/" + idPost);

		} else {
			// Si el postDTO es nulo, se podría manejar el error de alguna forma, por
			// ejemplo:
			log.error("El post con ID " + idPost + " no existe.");
			mav.setViewName("redirect:/foro");
		}
		return mav;
	}

}
