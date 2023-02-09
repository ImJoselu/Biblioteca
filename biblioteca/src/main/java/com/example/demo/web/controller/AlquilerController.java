package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.service.AlquilerService;

@Controller
public class AlquilerController {

	private static final Logger log = LoggerFactory.getLogger(AlquilerController.class);
	
	@Autowired
	private AlquilerService alquilerService;

	// Listar los alquileres
	@GetMapping("usuario/{idUsuario}/adminAlquiler")
	public ModelAndView findAll(@PathVariable Long idUsuario) {

		log.info("AlquilerController - findAll: Mostramos todos los alquileres");

		ModelAndView mav = new ModelAndView("adminAlquiler");
		List<AlquilerDTO> listaAlquileresDTO = alquilerService.findAll();
		mav.addObject("listaAlquileresDTO", listaAlquileresDTO);

		return mav;

	}
}
