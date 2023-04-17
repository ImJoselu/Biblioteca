package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.LibroDTO;
import com.example.demo.model.dto.SolicitudDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.LibroService;

@Controller
public class IndexController {

	@Autowired
	private LibroService libroService;

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	/*
	 * @Value("${aplicacion.nombre}") private String nombreAplicacion;
	 * 
	 * @Value("${asignatura}") private String nombreAsignatura;
	 */

	@GetMapping("/")
	public ModelAndView index() {

		log.info("IndexController - index: Mostramos la pagina inicial");

		ModelAndView mav = new ModelAndView("index");
		/*
		 * mav.addObject("titulo", nombreAplicacion); mav.addObject("nombreAsignatura",
		 * nombreAsignatura);
		 */

		List<LibroDTO> listaLibrosDTO = libroService.findTop6ByOrderByIdDesc();

		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId(1L); // ESTA LINEA HAY QUE CAMBIAR EL "0" POR EL ID DEL USUARIO LOGEADO EN EL MOMENTO
		mav.addObject("usuario", usuario);
		mav.addObject("listaLibrosDTO", listaLibrosDTO);

		return mav;
	}

	@GetMapping("/login")
	public ModelAndView login() {

		log.info("IndexController - index: Mostramos la pagina login");

		ModelAndView mav = new ModelAndView("login");
		/*
		 * mav.addObject("titulo", nombreAplicacion); mav.addObject("nombreAsignatura",
		 * nombreAsignatura);
		 */
		return mav;
	}

	@GetMapping("/contacto")
	public ModelAndView contacto() {

		log.info("IndexController - index: Mostramos la pagina contacto");

		ModelAndView mav = new ModelAndView("contacto");
		/*
		 * mav.addObject("titulo", nombreAplicacion); mav.addObject("nombreAsignatura",
		 * nombreAsignatura);
		 */
		UsuarioDTO usuario = new UsuarioDTO();
		SolicitudDTO solicitud = new SolicitudDTO();
		usuario.setId(1L); // ESTA LINEA HAY QUE CAMBIAR EL "0" POR EL ID DEL USUARIO LOGEADO EN EL MOMENTO
		mav.addObject("usuario", usuario);
		mav.addObject("solicitud", solicitud);
		
		return mav;
	}

}
