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


import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.SolicitudDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.EjemplarService;
import com.example.demo.service.SolicitudService;
import com.example.demo.service.UsuarioService;

@Controller
public class SolicitudController {

	@Autowired
	private SolicitudService solucitudService;
	
	private static final Logger log = LoggerFactory.getLogger(SolicitudController.class);
	
	@GetMapping("/adminContacto")
	public ModelAndView index() {
		
		log.info("SolicitudController - index: Mostramos la gestion de Solicitudes");
		
		List<SolicitudDTO> listaSolicitudesDTO = solucitudService.findAll();
		
		ModelAndView mav = new ModelAndView("adminContacto");
		mav.addObject("listaSolicitudesDTO", listaSolicitudesDTO);
		
		return mav;
	}
	
	@GetMapping("/MisSolicitudes/{idUsuario}")
	public ModelAndView misSolicitudes(@PathVariable("idUsuario") Long idUsuario) {
		
		log.info("SolicitudController - index: Mostramos las solicitudes del cliente: " + idUsuario);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		
		
		
		List<SolicitudDTO> listaSolicitudesDTO = solucitudService.findByUsuario(usuarioDTO);
		
		ModelAndView mav = new ModelAndView("adminSolicitudes");
		mav.addObject("listaSolicitudesDTO", listaSolicitudesDTO);
		
		return mav;
	}
	
	// Hacer solicitud
		@GetMapping("/usuario/{idUsuario}/solicitud/add")
		public ModelAndView add(@PathVariable("idUsuario") Long idUsuario) {
			
			log.info("ClienteController - add: Anyadimos una nueva solicitud para el cliente: " + idUsuario);
			
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setId(idUsuario);
			
			
			ModelAndView mav = new ModelAndView("contacto");
			mav.addObject("solicitudDTO", new SolicitudDTO());
			mav.addObject("usuarioDTO", usuarioDTO);
			
			return mav;
		}
		
		// Salvar clientes
		@PostMapping("/usuario/{idUsuario}/solicitud/save")
		public ModelAndView save(@ModelAttribute("solicitudDTO") SolicitudDTO solicitudDTO) {
			
			log.info("ClienteController - save: Salvamos los datos de la solicitud:" + solicitudDTO.toString());
			
			// Invocamos a la capa de servicios para que almacene los datos del cliente
			solucitudService.save(solicitudDTO); 
			
			// Redireccionamos para volver a invocar el metodo que escucha /clientes
			ModelAndView mav = new ModelAndView("redirect:/MisSolicitudes/{idUsuario}");		
			return mav;
		}
		
	
}
