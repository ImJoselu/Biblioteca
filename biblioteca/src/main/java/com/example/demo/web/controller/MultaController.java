package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.MultaDTO;
import com.example.demo.service.MultaService;

public class MultaController {
private static final Logger log = LoggerFactory.getLogger(MultaController.class);
	
	@Autowired
	private MultaService multaService;

	// Listar los multas
	@GetMapping("usuario/{idUsuario}/adminMulta")
	public ModelAndView findAll(@PathVariable Long idUsuario) {

		log.info("MultaController - findAll: Mostramos todos los multas");

		ModelAndView mav = new ModelAndView("adminMulta");
		List<MultaDTO> listaMultasDTO = multaService.findAll();
		mav.addObject("listaMultasDTO", listaMultasDTO);

		return mav;

	}
}
