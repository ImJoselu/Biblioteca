package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.EjemplarService;
import com.example.demo.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@GetMapping("/adminClientes")
	public ModelAndView adminClientes() {

		log.info("UsuarioController - index: Mostramos la gestion de clientes");

		List<UsuarioDTO> listaClientesDTO = usuarioService.findAllClientes();

		ModelAndView mav = new ModelAndView("adminClientes");
		mav.addObject("listaClientesDTO", listaClientesDTO);

		return mav;
	}

	@GetMapping("/usuario/{idUsuario}/adminEditarUsuarios")
	public ModelAndView adminEditarUsuarios(@PathVariable Long idUsuario) {

		log.info("UsuarioController - adminEditarUsuarios: editamos la gestion de usuarios");
		ModelAndView mav = new ModelAndView("adminEditarUsuarios");
		mav.addObject("usuario", usuarioService.findById(idUsuario));
		
		return mav;
	}

}
