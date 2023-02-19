package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.service.EjemplarService;

@Controller
public class EjemplarController {

	@Autowired
	private EjemplarService ejemplarService;

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@GetMapping("/adminEjemplares")
	public ModelAndView index() {

		log.info("EjemplarController - index: Mostramos la gestion de ejemplares");

		List<EjemplarDTO> listaEjemplaresDTO = ejemplarService.findAll();

		ModelAndView mav = new ModelAndView("adminEjemplares");
		mav.addObject("listaEjemplaresDTO", listaEjemplaresDTO);

		return mav;
	}

}
