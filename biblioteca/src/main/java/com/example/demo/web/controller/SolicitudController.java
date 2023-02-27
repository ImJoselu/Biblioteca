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

import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.model.dto.SolicitudDTO;
import com.example.demo.service.SolicitudService;
import com.example.demo.service.UsuarioService;

@Controller
public class SolicitudController {

	@Autowired
	private SolicitudService solucitudService;

	@Autowired
	private UsuarioService usuarioService;

	private static final Logger log = LoggerFactory.getLogger(SolicitudController.class);

	/* Index - la id, y la vista el select y el isbn */

	@GetMapping("/adminContacto")
	public ModelAndView index() {

		log.info("SolicitudController - index: Mostramos la gestion de Solicitudes");

		List<SolicitudDTO> listaSolicitudesDTO = solucitudService.findAll();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId((Long.valueOf(1)));
		ModelAndView mav = new ModelAndView("adminContacto");

		// Obtenemos el cliente
		mav.addObject("listaSolicitudesDTO", listaSolicitudesDTO);
		mav.addObject("usuarioDTO", usuarioDTO);
		return mav;
	}

	@PostMapping("/solicitud/save")
	public ModelAndView save(@ModelAttribute("solicitudDTO") SolicitudDTO solicitudDTO) {
		log.info("SolicitudController - save: Salvamos los datos de la solicitud:" + solicitudDTO.toString());          
		// Invocamos a la capa de servicios para que almacene los datos del usuario         
		solucitudService.save(solicitudDTO);          
		// Redireccionamos para volver a invocar el metodo que escucha /usuarios        
		ModelAndView mav = new ModelAndView("redirect:/adminContacto");         
		return mav;     
		}
	

	@GetMapping("/MisSolicitudes/{idUsuario}")
	public ModelAndView misSolicitudes(@PathVariable("idUsuario") Long idUsuario) {

		log.info("SolicitudController - index: Mostramos las solicitudes del usuario: " + idUsuario);
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

		log.info("SolicitudController - add: Anyadimos una nueva solicitud para el usuario: " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);

		ModelAndView mav = new ModelAndView("contacto");
		mav.addObject("solicitudDTO", new SolicitudDTO());
		mav.addObject("usuarioDTO", usuarioDTO);

		return mav;
	}

	// Salvar usuarios
	@PostMapping("/usuario/{idUsuario}/solicitud/save")
	public ModelAndView nuevaSolicitud(@ModelAttribute("solicitud") SolicitudDTO solicitudDTO,
			@PathVariable("idUsuario") Long idUsuario) {

		log.info("SolicitudController - save: Salvamos los datos de la solicitud:" + solicitudDTO.toString());

		// Invocamos a la capa de servicios para que almacene los datos del usuario
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		solicitudDTO.setUsuarioDTO(usuarioDTO);

		solucitudService.nuevaSolicitud(solicitudDTO);

		// Redireccionamos para volver a invocar el metodo que escucha /usuarios
		ModelAndView mav = new ModelAndView("redirect:/adminIndex");
		return mav;
	}

	// Updatear AdminContacto
	@GetMapping("/adminContacto/{idSolicitud}/adminContactoform")
	public ModelAndView updateEstado(@PathVariable("idSolicitud") Long idSolicitud) {

		log.info("SolicitudController - save:Salvamos los datos del solicitud");

		ModelAndView mav = new ModelAndView("adminContactoform");
		SolicitudDTO solicitudDTO = new SolicitudDTO();
		solicitudDTO.setId(idSolicitud);
		solicitudDTO = solucitudService.findById(solicitudDTO);
		mav.addObject("solicitudDTO", solicitudDTO);

		return mav;
	}

}
