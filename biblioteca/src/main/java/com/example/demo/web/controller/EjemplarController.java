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
import com.example.demo.model.dto.LibroDTO;
import com.example.demo.service.EjemplarService;
import com.example.demo.service.LibroService;

@Controller
public class EjemplarController {

	@Autowired
	private LibroService libroService;
	
	@Autowired
	private EjemplarService ejemplarService;

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	// RUTA DEL LIBRO /adminEjemplares CORREGIR
	@GetMapping("/adminLibros/{isbnLibro}/adminEjemplares")
	public ModelAndView index(@PathVariable("isbnLibro") String isbnLibro) {

		log.info("EjemplarController - index: Mostramos la gestion de ejemplares");
		LibroDTO libroDTO = new LibroDTO();
		libroDTO.setIsbn(isbnLibro);
		List<EjemplarDTO> listaEjemplaresDTO = ejemplarService.findByLibro(libroDTO);

		ModelAndView mav = new ModelAndView("adminEjemplares");
		
		mav.addObject("libroDTO", libroDTO);
		mav.addObject("listaEjemplaresDTO", listaEjemplaresDTO);

		return mav;
	}

}
