package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.MultaDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.entity.Usuario;
import com.example.demo.service.MultaService;

public class MultaController {
private static final Logger log = LoggerFactory.getLogger(MultaController.class);
	
	@Autowired
	private MultaService multaService;

	// Listar los multas
	@GetMapping("usuario/{idUsuario}/adminMulta")
	public ModelAndView findAllByUsuario(@PathVariable Long idUsuario) {

		log.info("MultaController - findAll: Mostramos todos los multas del usuario: " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		
		
		ModelAndView mav = new ModelAndView("adminMulta");
		List<MultaDTO> listaMultasDTO = multaService.findAllByAlquiler(usuarioDTO);
		mav.addObject("listaMultasDTO", listaMultasDTO);

		return mav;

	}
	
	@GetMapping("/usuario/{idUsuario}/multas/add")
	public ModelAndView add(@PathVariable Long idUsuario) {
		
		log.info("CuentaController - add: Alta de multa del usuario: " + idUsuario);
		
		// Obtenemos el usuario para luego poner sus datos en la pantalla
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		
		// pasamos el usuario y la nueva multa a la vista
		ModelAndView mav = new ModelAndView("multaform");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("multaDTO", new MultaDTO());
		mav.addObject("add", true);
		return mav;
	}
	
	@PostMapping("/usuario/{idUsuario}/multas/save")
	public ModelAndView save(@PathVariable Long idUsuario, @ModelAttribute("multaDTO") MultaDTO multaDTO) {
		
		log.info("CuentaController - save: Salvando la multa del usuario: " + idUsuario);
		
		// Obtenemos el usuario para luego poner sus datos en la pantalla
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		// Asignamos a la multa el usuario (no hace falta buscarlo ya que al salvarlo lo buscaremos)
		multaDTO.setUsuarioDTO(usuarioDTO);

		// invocamos la operacion save a la capa de servicio de multa
		multaService.save(multaDTO);
		// Retornamos a la lista de multas del usuario
		ModelAndView mav = new ModelAndView("redirect:/usuario/{idUsuario}/adminMultas");
		return mav;
	}
	
	// Borrar un usuario
		@GetMapping("/usuario/{idUsuario}/multas/{idMulta}")
		public ModelAndView delete(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idMulta") Long idMulta) {
			
			log.info("MultaController - delete: Borramos la multa:" + idMulta);
			
			// Creamos un usuario y le asignamos el id. Este usuario es el que se va a borrar
			MultaDTO multaDTO = new MultaDTO();
			multaDTO.setId(idMulta);
			multaService.delete(multaDTO);
			
			// Redireccionamos para volver a invocar al metodo que escucha /usuario
			ModelAndView mav = new ModelAndView("redirect:/usuario/{idUsuario}/multas");
			
			return mav;
		}	
}
