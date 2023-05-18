package com.example.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.model.dto.EstadisticaDTO;
import com.example.demo.model.dto.SolicitudDTO;
import com.example.demo.service.EstadisticasService;


@Controller
public class EstadisticasController {

	@Autowired
	EstadisticasService estadisticasService;
	
	private static final Logger log = LoggerFactory.getLogger(EstadisticasController.class);
	
	@GetMapping("/estadisticas/{anyo}")
	public ModelAndView estadisticas(@PathVariable("anyo") Integer anyo) {

	log.info("IndexController - index: Mostramos la pagina inicial");

	ModelAndView mav = new ModelAndView("estadisticas");
		
	EstadisticaDTO genPopular = estadisticasService.generosPopulares();
	EstadisticaDTO libPopular = estadisticasService.librosPopulares();
	EstadisticaDTO alqMes = estadisticasService.alquileresPorMes(anyo);
	
	
	mav.addObject("genPopular", genPopular);
	mav.addObject("libPopular", libPopular);
	mav.addObject("alqMes", alqMes);
	
	return mav;

	}
}
