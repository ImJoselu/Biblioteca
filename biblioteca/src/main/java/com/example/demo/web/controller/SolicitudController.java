package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.SolicitudDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.EjemplarService;
import com.example.demo.service.SolicitudService;
import com.example.demo.service.UsuarioService;

public class SolicitudController {

	@Autowired
	private SolicitudService solicitudService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private static final Logger log = LoggerFactory.getLogger(SolicitudController.class);
	
	@GetMapping("/adminSolicitudes")
	public ModelAndView index() {
		
		log.info("SolicitudController - index: Mostramos la gestion de Solicitudes");
		
		List<SolicitudDTO> listaSolicitudesDTO = solicitudService.findAll();
		
		ModelAndView mav = new ModelAndView("adminSolicitudes");
		mav.addObject("listaSolicitudesDTO", listaSolicitudesDTO);
		
		return mav;
	}
	
	@GetMapping("/MisSolicitudes/{idCliente}")
	public ModelAndView misSolicitudes(@PathVariable("idCliente") Long idCliente) {
		
		log.info("SolicitudController - index: Mostramos las solicitudes del cliente: " + idCliente);
		
		UsuarioDTO usuarioDTO = usuarioService.findById(idCliente);
		
		List<SolicitudDTO> listaSolicitudesDTO = solicitudService.findByUsuario(usuarioDTO);
		
		ModelAndView mav = new ModelAndView("adminSolicitudes");
		mav.addObject("listaSolicitudesDTO", listaSolicitudesDTO);
		
		return mav;
	}
	
}
