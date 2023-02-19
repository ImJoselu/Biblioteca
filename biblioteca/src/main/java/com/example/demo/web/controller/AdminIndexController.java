package com.example.demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminIndexController {

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@GetMapping("/adminIndex")
	public ModelAndView index() {

		log.info("IndexController - index: Mostramos la pagina inicial");

		ModelAndView mav = new ModelAndView("adminIndex");
		/*
		 * mav.addObject("titulo", nombreAplicacion); mav.addObject("nombreAsignatura",
		 * nombreAsignatura);
		 */
		return mav;
	}

}
