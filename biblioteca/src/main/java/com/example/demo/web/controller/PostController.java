package com.example.demo.web.controller;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.CommentDTO;
import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.LibroDTO;
import com.example.demo.model.dto.PostDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.CommentService;
import com.example.demo.service.EjemplarService;
import com.example.demo.service.PostService;
import com.example.demo.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UsuarioService usuarioService;

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@GetMapping("/foro")
	public ModelAndView foro() {

		log.info("PostController - index: Mostramos el foro");

		List<PostDTO> listaPostsDTO = postService.findAll();

		ModelAndView mav = new ModelAndView("foro");

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(1L); // ESTA LINEA HAY QUE CAMBIAR EL "1" POR EL ID DEL USUARIO LOGEADO EN EL MOMENTO

		mav.addObject("nuevoPostDTO", new PostDTO());
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("listaPostsDTO", listaPostsDTO);
		return mav;
	}

	
	
	
	
	
	
	
	
	/*
	 * @GetMapping("/foro/{idPost}") public ModelAndView
	 * mostrarComments(@PathVariable Long idPost) {
	 * 
	 * log.info("PostController - index: Mostramos el post " + idPost);
	 * 
	 * // Obtenemos el usuario para luego poner sus datos en la pantalla UsuarioDTO
	 * usuarioDTO = new UsuarioDTO(); usuarioDTO.setId(1L);
	 * 
	 * PostDTO postDTO = new PostDTO(); postDTO = postService.findById(idPost);
	 * 
	 * 
	 * List<CommentDTO> listaComentariosDTO = commentService.findAllByPost(postDTO);
	 * 
	 * 
	 * 
	 * ModelAndView mav = new ModelAndView("post");
	 * 
	 * mav.addObject("usuarioDTO", usuarioDTO); mav.addObject("postDTO", postDTO);
	 * mav.addObject("listaComentariosDTO", listaComentariosDTO);
	 * 
	 * return mav; }
	 */

	@GetMapping("/foro/{idPost}")
	public ModelAndView mostrarComments(@PathVariable Long idPost) {

		log.info("PostController - mostrarComments: Mostramos el post " + idPost);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(1L); // Cambiar por el ID del usuario logeado en el momento

		PostDTO postDTO = postService.findById(idPost);
		List<CommentDTO> listaComentariosDTO = commentService.findAllByPost(postDTO);

		ModelAndView mav = new ModelAndView("post");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("postDTO", postDTO);
		mav.addObject("listaComentariosDTO", listaComentariosDTO);
		mav.addObject("nuevoComentarioDTO", new CommentDTO());

		return mav;
	}

	@GetMapping("/foro/{idPost}/like")
	public ModelAndView darLike(@PathVariable Long idPost) {

		log.info("PostController - darLike:Damos lika al post " + idPost);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(1L); // Cambiar por el ID del usuario logeado en el momento

		PostDTO postDTO = postService.findById(idPost);
		postDTO.setLikesDTO(postDTO.getLikesDTO() + 1);
		postService.save(postDTO);

		ModelAndView mav = new ModelAndView("post");
		mav.setViewName("redirect:/foro/" + idPost);

		return mav;
	}

	@PostMapping("/foro/{idPost}/comentar/{usernameUsuario}")
	public ModelAndView comentarPost(@PathVariable Long idPost,
			@ModelAttribute("nuevoComentarioDTO") CommentDTO comentarioDTO, @PathVariable("usernameUsuario") String usernameUsuario) {
		log.info("PostController - comentarPost: Se intenta crear un comentario en el post " + idPost);

		ModelAndView mav = new ModelAndView("");

		UsuarioDTO usuarioDTO = usuarioService.findByName(usernameUsuario); // Cambiar por el ID del usuario logeado en el momento

		comentarioDTO.setUsuarioDTO(usuarioDTO);

		PostDTO postDTO = postService.findById(idPost);

		if (postDTO != null) {
			comentarioDTO.setPostDTO(postDTO);

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

	@PostMapping("/foro/nuevo/{usernameUsuario}")
	public ModelAndView crearPost(@ModelAttribute("nuevoPostDTO") PostDTO postDTO, @PathVariable("usernameUsuario") String usernameUsuario) {
		log.info("PostController - crearPost: Se intenta crear un post ");

		ModelAndView mav = new ModelAndView("");

		UsuarioDTO usuarioDTO = usuarioService.findByName(usernameUsuario); // Cambiar por el ID del usuario logeado en el momento
		postDTO.setUsuarioDTO(usuarioDTO);

		postService.save(postDTO);
		mav.setViewName("redirect:/foro");

		return mav;
	}

}
