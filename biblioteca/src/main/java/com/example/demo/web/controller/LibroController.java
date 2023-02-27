package com.example.demo.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.LibroDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.EjemplarService;
import com.example.demo.service.LibroService;

@Controller
public class LibroController {

	@Autowired
	private LibroService libroService;

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@GetMapping("/tienda")
	public ModelAndView tienda() {

		log.info("LibroController - index: Mostramos la gestion de ejemplares");

		List<LibroDTO> listaLibrosDTO = libroService.findAll();

		List<LibroDTO> listaAleatoria = new ArrayList<>();

		// Obtenemos tres índices aleatorios
		Random random = new Random();
		Set<Integer> indices = new HashSet<>();
		while (indices.size() < 3) {
			indices.add(random.nextInt(listaLibrosDTO.size()));
		}

		// Añadimos los libros correspondientes a los índices aleatorios a la lista
		// nueva
		for (Integer indice : indices) {
			listaAleatoria.add(listaLibrosDTO.get(indice));
		}

		ModelAndView mav = new ModelAndView("tienda");

		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId(1L); // ESTA LINEA HAY QUE CAMBIAR EL "1" POR EL ID DEL USUARIO LOGEADO EN EL MOMENTO
		mav.addObject("usuario", usuario);
		mav.addObject("listaLibrosDTO", listaLibrosDTO);
		mav.addObject("listaPopulares", listaAleatoria);

		return mav;
	}

}
