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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.MultaDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.entity.LibroRebeca;
import com.example.demo.service.LibroService;
import com.example.demo.service.RebecaService;

@Controller
public class RebecaController {
	
	private static final Logger log = LoggerFactory.getLogger(RebecaController.class);
	
	@Autowired
	private RebecaService rebecaService;

	// Listar los alquileres
	@GetMapping("/buscador")
	public ModelAndView buscador() {

		log.info("RebecaController - Entramos al buscador");
		LibroRebeca libroRebeca = new LibroRebeca();
		
		ModelAndView mav = new ModelAndView("buscador");
		
		mav.addObject("libroRebeca", libroRebeca);
		return mav;

	}
	
	@PostMapping("/buscador")
	public ModelAndView buscador(@RequestParam("isbn") String isbn13) {

		log.info("RebecaController - mostramos los datos del libro con isbn: " + isbn13);

		LibroRebeca libroRebeca = new LibroRebeca();
		libroRebeca = rebecaService.performSearch(isbn13);
		
		System.out.println(libroRebeca);

		ModelAndView mav = new ModelAndView("buscador");
		
		//Tal vez esto sea innecesario y solo haya que mandar el objeto libroRebeca a la vista
		if(libroRebeca == null) {
			mav.addObject("existe", false);
		}else {
			mav.addObject("existe", true);
		}
		
		mav.addObject("libroRebeca", libroRebeca);

		return mav;

	}
	
	@PostMapping("/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas/save")
	public ModelAndView saveLibroRebeca(@ModelAttribute("libroRebeca") LibroRebeca libroRebeca) {

		log.info("RebecaController - save: Salvando el libro: " + libroRebeca.getIsbn13());

		// invocamos la operacion save a la capa de servicio de multa
		boolean guardado = rebecaService.save(libroRebeca);
		// Retornamos a la lista de multas del usuario
		ModelAndView mav = new ModelAndView("buscador");
		mav.addObject("guardado", guardado);
		return mav;
	}


}
