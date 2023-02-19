package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.EjemplarService;
import com.example.demo.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@GetMapping("/adminClientes")
	public ModelAndView adminClientes() {

		log.info("EjemplarController - index: Mostramos la gestion de clientes");

		List<UsuarioDTO> listaClientesDTO = usuarioService.findAllClientes();

		ModelAndView mav = new ModelAndView("adminClientes");
		mav.addObject("listaClientesDTO", listaClientesDTO);

		return mav;
	}

}
