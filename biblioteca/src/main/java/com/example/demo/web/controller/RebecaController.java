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
	
	@PostMapping("/buscador/save")
	public ModelAndView saveLibroRebeca(@ModelAttribute("libroRebeca") LibroRebeca libroRebeca) {

		log.info("RebecaController - save: Salvando el libro: " + libroRebeca.getIsbn13());
		ModelAndView mav = new ModelAndView();
		// invocamos la operacion save a la capa de servicio de multa
		
		System.out.println("Longitud: " + libroRebeca.getIsbn13().length());

		boolean seguir = !libroRebeca.getIsbn13().isEmpty() && !libroRebeca.getTitulo().isEmpty()
				 && !(libroRebeca.getIsbn13().length() != 17);
		
		if (!seguir) {
			mav.addObject("libroRebeca", libroRebeca);
			mav.addObject("error", true);
			mav.setViewName("buscador");
		} else {
			
			boolean guardado = rebecaService.save(libroRebeca);
			
			if(guardado == false) {
				mav.addObject("libroRebeca", libroRebeca);
				mav.addObject("repetido", true);
				mav.setViewName("buscador");
			}
			
			mav.addObject("guardado", true);
			mav.setViewName("buscador");
		}
		
		return mav;
	}


}
