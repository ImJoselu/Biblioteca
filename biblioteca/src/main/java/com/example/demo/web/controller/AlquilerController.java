package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.LibroDTO;
import com.example.demo.model.dto.MultaDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.entity.Ejemplar;
import com.example.demo.repository.entity.Usuario;
import com.example.demo.service.AlquilerService;

@Controller
public class AlquilerController {

	private static final Logger log = LoggerFactory.getLogger(AlquilerController.class);

	@Autowired
	private AlquilerService alquilerService;

	// Listar los alquileres
	@GetMapping("/usuario/{idUsuario}/adminAlquiler")
	public ModelAndView findAllByUsuario(@PathVariable Long idUsuario) {

		log.info("AlquilerController - findAll: Mostramos todos los alquileres del usuario: " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);

		ModelAndView mav = new ModelAndView("adminAlquiler");
		List<AlquilerDTO> listaAlquileresDTO = alquilerService.findAllByUsuario(usuarioDTO);
		mav.addObject("listaAlquileresDTO", listaAlquileresDTO);
		mav.addObject("usuarioDTO", usuarioDTO);

		return mav;

	}

	@GetMapping("/usuario/{idUsuario}/adminAlquiler/add")
	public ModelAndView add(@PathVariable Long idUsuario) {

		log.info("CuentaController - add: Alta de alquiler del usuario: " + idUsuario);

		// Obtenemos el usuario para luego poner sus datos en la pantalla
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);

		// pasamos el usuario y la nueva multa a la vista
		ModelAndView mav = new ModelAndView("alquilerForm");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("alquilerDTO", new AlquilerDTO());
		mav.addObject("add", true);
		return mav;
	}

	@PostMapping("/usuario/{idUsuario}/adminAlquiler/save")
	public ModelAndView save(@PathVariable Long idUsuario, @ModelAttribute("alquilerDTO") AlquilerDTO alquilerDTO) {

		log.info("CuentaController - save: Salvando el alquiler del usuario: " + idUsuario);

		// Obtenemos el usuario para luego poner sus datos en la pantalla
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		// Asignamos a la alquiler el usuario (no hace falta buscarlo ya que al salvarlo
		// lo buscaremos)
		alquilerDTO.setUsuarioDTO(usuarioDTO);

		// invocamos la operacion save a la capa de servicio de alquiler
		alquilerService.save(alquilerDTO);
		// Retornamos a la lista de alquilers del usuario
		ModelAndView mav = new ModelAndView("redirect:/usuario/{idUsuario}/adminAlquilers");
		return mav;
	}

	@GetMapping("/usuario/{idUsuario}/alquilar/{idLibro}")
	public ModelAndView alquilar(@PathVariable Long idUsuario, @PathVariable String idLibro) {

		log.info("CuentaController - add: Alta de alquiler del usuario: " + idUsuario);

		// Obtenemos el usuario para luego poner sus datos en la pantalla
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);

		LibroDTO libroDTO = new LibroDTO();
		libroDTO.setIsbn(idLibro);

		EjemplarDTO ejemplarDTO = alquilerService.alquilar(usuarioDTO, libroDTO);
		

		
		
		
		
		
		ModelAndView mav = new ModelAndView("confirmacion");
		
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("existe", ejemplarDTO.getId());
		mav.addObject("ejemplarDTO", ejemplarDTO);
		return mav;
	}
	
	
	

}
