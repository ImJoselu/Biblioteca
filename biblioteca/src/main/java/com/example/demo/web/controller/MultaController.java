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
import com.example.demo.model.dto.MultaDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.entity.Usuario;
import com.example.demo.service.MultaService;

@Controller
public class MultaController {
	private static final Logger log = LoggerFactory.getLogger(MultaController.class);

	@Autowired
	private MultaService multaService;

	// Listar los multas
	@GetMapping("/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas")
	public ModelAndView findAllByAlquiler(@PathVariable Long idUsuario, @PathVariable Long idAlquiler) {

		log.info("MultaController - findAll: Mostramos todos los multas del usuario: " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		AlquilerDTO alquilerDTO = new AlquilerDTO();
		alquilerDTO.setId(idAlquiler);

		alquilerDTO.setUsuarioDTO(usuarioDTO);

		ModelAndView mav = new ModelAndView("adminMultas");
		List<MultaDTO> listaMultasDTO = multaService.findAllByAlquiler(alquilerDTO);
		mav.addObject("listaMultasDTO", listaMultasDTO);
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("alquilerDTO", alquilerDTO);

		return mav;

	}

	// Listar los multas
	@GetMapping("/usuario/{idUsuario}/adminMultas")
	public ModelAndView findAllByAlquiler(@PathVariable Long idUsuario) {

		log.info("MultaController - findAll: Mostramos todos los multas del usuario: " + idUsuario);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);

		ModelAndView mav = new ModelAndView("adminMultas");
		List<MultaDTO> listaMultasDTO = multaService.findAllByUsuario(usuarioDTO);
		mav.addObject("listaMultasDTO", listaMultasDTO);
		mav.addObject("usuarioDTO", usuarioDTO);
		return mav;

	}

	@GetMapping("/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas/add")
	public ModelAndView add(@PathVariable Long idUsuario, @PathVariable Long idAlquiler) {

		log.info("CuentaController - add: Alta de multa del usuario: " + idUsuario);

		// Obtenemos el usuario para luego poner sus datos en la pantalla
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);

		AlquilerDTO alquilerDTO = new AlquilerDTO();
		alquilerDTO.setId(idAlquiler);

		alquilerDTO.setUsuarioDTO(usuarioDTO);

		// pasamos el usuario y la nueva multa a la vista
		ModelAndView mav = new ModelAndView("multasForm");
		mav.addObject("usuarioDTO", usuarioDTO);
		mav.addObject("alquilerDTO", alquilerDTO);
		mav.addObject("multaDTO", new MultaDTO());
		mav.addObject("add", true);
		return mav;
	}

	@PostMapping("/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas/save")
	public ModelAndView save(@PathVariable Long idUsuario, @PathVariable Long idAlquiler,
			@ModelAttribute("multaDTO") MultaDTO multaDTO) {

		log.info("CuentaController - save: Salvando la multa del usuario: " + idUsuario);

		// Obtenemos el usuario para luego poner sus datos en la pantalla
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		AlquilerDTO alquilerDTO = new AlquilerDTO();
		alquilerDTO.setId(idAlquiler);

		alquilerDTO.setUsuarioDTO(usuarioDTO);
		// Asignamos a la multa el usuario (no hace falta buscarlo ya que al salvarlo lo
		// buscaremos)
		multaDTO.setAlquilerDTO(alquilerDTO);

		// invocamos la operacion save a la capa de servicio de multa
		multaService.save(multaDTO);
		// Retornamos a la lista de multas del usuario
		ModelAndView mav = new ModelAndView("redirect:/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas");
		return mav;
	}

	// Borrar un usuario

	@GetMapping("/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas/delete/{idMulta}")
	public ModelAndView delete(@PathVariable Long idUsuario, @PathVariable Long idAlquiler,
			@PathVariable("idMulta") Long idMulta) {

		log.info("MultaController - delete: Borramos la multa:" + idMulta);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);
		AlquilerDTO alquilerDTO = new AlquilerDTO();
		alquilerDTO.setId(idAlquiler);

		alquilerDTO.setUsuarioDTO(usuarioDTO);
		// Creamos un usuario y le asignamos el id. Este usuario es el que se va a
		// borrar
		MultaDTO multaDTO = new MultaDTO();
		multaDTO.setId(idMulta);
		multaDTO.setAlquilerDTO(alquilerDTO);
		multaService.delete(multaDTO);

		// Redireccionamos para volver a invocar al metodo que escucha /usuario
		ModelAndView mav = new ModelAndView("redirect:/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas");

		return mav;
	}

	@GetMapping("/usuario/{idUsuario}/adminMultas/{idMulta}/descartar")
	public ModelAndView descartar(@PathVariable Long idUsuario, @PathVariable Long idMulta) {

		log.info("MultaController - delete: Borramos la multa:" + idMulta);

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(idUsuario);

		// Creamos un usuario y le asignamos el id. Este usuario es el que se va a
		// borrar
		MultaDTO multaDTO = new MultaDTO();
		multaDTO.setId(idMulta);

		multaService.descartar(multaDTO);

		// Redireccionamos para volver a invocar al metodo que escucha /usuario
		ModelAndView mav = new ModelAndView("redirect:/usuario/{idUsuario}/adminMultas");

		return mav;
	}
}
