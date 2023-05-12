package com.example.demo.web.controller;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

//	@GetMapping("/tienda")
//	public ModelAndView tienda() {
//
//		log.info("LibroController - index: Mostramos la gestion de ejemplares");
//
//		List<LibroDTO> listaLibrosDTO = libroService.findAll();
//
//		List<LibroDTO> listaAleatoria = new ArrayList<>();
//
//		// Obtenemos tres índices aleatorios
//		Random random = new Random();
//		Set<Integer> indices = new HashSet<>();
//		while (indices.size() < 3) {
//			indices.add(random.nextInt(listaLibrosDTO.size()));
//		}
//
//		// Añadimos los libros correspondientes a los índices aleatorios a la lista
//		// nueva
//		for (Integer indice : indices) {
//			listaAleatoria.add(listaLibrosDTO.get(indice));
//		}
//
//		ModelAndView mav = new ModelAndView("tienda");
//
//		UsuarioDTO usuario = new UsuarioDTO();
//		usuario.setId(1L); // ESTA LINEA HAY QUE CAMBIAR EL "1" POR EL ID DEL USUARIO LOGEADO EN EL MOMENTO
//		mav.addObject("usuario", usuario);
//		mav.addObject("listaLibrosDTO", listaLibrosDTO);
//		mav.addObject("listaPopulares", listaAleatoria);
//
//		return mav;
//	}

//		log.info("LibroController - index: Mostramos la gestion de libroes");
//	@GetMapping("/tienda/filtrada")
//	public ModelAndView tiendaFiltros(@RequestParam(value = "filtro", required = false) String filtro,
//
//			@RequestParam(value = "orden", required = false) String orden) {
//
//		log.info("LibroController - tiendaFiltros: Mostramos la tienda filtrada por filtro: {} y orden: {}", filtro,
//				orden);
//
//		List<LibroDTO> listaLibrosDTO = libroService.findAll();
//
//		List<LibroDTO> listaAleatoria = new ArrayList<>();
//
//		// Obtenemos tres índices aleatorios
//		Random random = new Random();
//		Set<Integer> indices = new HashSet<>();
//		while (indices.size() < 3) {
//			indices.add(random.nextInt(listaLibrosDTO.size()));
//		}
//
//		// Añadimos los libros correspondientes a los índices aleatorios a la lista
//		// nueva
//		for (Integer indice : indices) {
//			listaAleatoria.add(listaLibrosDTO.get(indice));
//		}
//
//		List<LibroDTO> listaLibrosFiltrada = libroService.findAll();
//
//		// Aplicamos el filtro si existe
//		if (filtro != null && !filtro.isEmpty()) {
//			listaLibrosFiltrada = listaLibrosFiltrada.stream()
//					.filter(libro -> libro.getTitulo().toLowerCase().contains(filtro.toLowerCase())
//							|| libro.getEditorialDTO().getNombre().toLowerCase().contains(filtro.toLowerCase())
//							|| libro.getIsbn().toLowerCase().contains(filtro.toLowerCase()))
//					.collect(Collectors.toList());
//		}
//
//		// Aplicamos el orden si existe
//		if (orden != null && !orden.isEmpty()) {
//			switch (orden) {
//			case "ascendente":
//				listaLibrosFiltrada.sort(Comparator.comparing(LibroDTO::getTitulo));
//				break;
//			case "descendente":
//				listaLibrosFiltrada.sort(Comparator.comparing(LibroDTO::getTitulo).reversed());
//				break;
//			case "editorial-ascendente":
//				listaLibrosFiltrada.sort(Comparator.comparing(libro -> libro.getEditorialDTO().getNombre()));
//				break;
//			case "editorial-descendente":
//				listaLibrosFiltrada.sort(
//						Comparator.comparing(libro -> ((LibroDTO) libro).getEditorialDTO().getNombre()).reversed());
//				break;
//			}
//		}
//
//		ModelAndView mav = new ModelAndView("tienda");
//
//		UsuarioDTO usuario = new UsuarioDTO();
//		usuario.setId(1L);
//		// ESTA LINEA HAY QUE CAMBIAR EL "1" POR EL ID DEL USUARIO LOGEADO EN EL MOMENTO
//		mav.addObject("usuario", usuario);
//		mav.addObject("listaLibrosDTO", listaLibrosFiltrada);
//		mav.addObject("listaPopulares", listaAleatoria);
//
//		return mav;
//	}
	@GetMapping("/tienda/filtrada")
	public ModelAndView tiendaFiltros(@RequestParam(value = "filtro", required = false) String filtro,

			@RequestParam(value = "orden", required = false) String orden,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "4") int size) {

		log.info("LibroController - tiendaFiltros: Mostramos la tienda filtrada por filtro: {} y orden: {}", filtro,
				orden);

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

		List<LibroDTO> listaLibrosFiltrada = libroService.findAll();

		// Aplicamos el filtro si existe
		if (filtro != null && !filtro.isEmpty()) {
			listaLibrosFiltrada = listaLibrosFiltrada.stream()
					.filter(libro -> libro.getTitulo().toLowerCase().contains(filtro.toLowerCase())
							|| libro.getEditorialDTO().getNombre().toLowerCase().contains(filtro.toLowerCase())
							|| libro.getIsbn().toLowerCase().contains(filtro.toLowerCase()))
					.collect(Collectors.toList());
		}

		// Aplicamos el orden si existe
		if (orden != null && !orden.isEmpty()) {
			switch (orden) {
			case "ascendente":
				listaLibrosFiltrada.sort(Comparator.comparing(LibroDTO::getTitulo));
				break;
			case "descendente":
				listaLibrosFiltrada.sort(Comparator.comparing(LibroDTO::getTitulo).reversed());
				break;
			case "editorial-ascendente":
				listaLibrosFiltrada.sort(Comparator.comparing(libro -> libro.getEditorialDTO().getNombre()));
				break;
			case "editorial-descendente":
				listaLibrosFiltrada.sort(
						Comparator.comparing(libro -> ((LibroDTO) libro).getEditorialDTO().getNombre()).reversed());
				break;
			}
		}

		int start = page * size;
		int end = Math.min(start + size, listaLibrosFiltrada.size());
		List<LibroDTO> subListaLibrosFiltrada = listaLibrosFiltrada.subList(start, end);

		ModelAndView mav = new ModelAndView("tienda");

		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId(1L);
		// ESTA LINEA HAY QUE CAMBIAR EL "1" POR EL ID DEL USUARIO LOGEADO EN EL MOMENTO
		mav.addObject("usuario", usuario);
		mav.addObject("listaPopulares", listaAleatoria);
		return mav;
	}

	@GetMapping("/adminLibros")
	public ModelAndView index() {

		log.info("LibroController - index: Mostramos la gestion de libros");

		List<LibroDTO> listaLibrosDTO = libroService.findAll();

		ModelAndView mav = new ModelAndView("adminLibros");
		mav.addObject("listaLibrosDTO", listaLibrosDTO);

		mav.addObject("listaLibrosDTO", subListaLibrosFiltrada);
		mav.addObject("totalPages", (listaLibrosFiltrada.size() + size - 1) / size);
		mav.addObject("currentPage", page);

		return mav;
	}
	@GetMapping("/tienda")
	public ModelAndView mostrarCatalogo(@RequestParam(value = "filtro", required = false) String filtro,
			
			@RequestParam(value = "orden", required = false) String orden,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "4") int size) {
		
		log.info("LibroController - tiendaFiltros: Mostramos la tienda filtrada por filtro: {} y orden: {}", filtro,
				orden);
		
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
		
		List<LibroDTO> listaLibrosFiltrada = libroService.findAll();
		
		// Aplicamos el filtro si existe
		if (filtro != null && !filtro.isEmpty()) {
			listaLibrosFiltrada = listaLibrosFiltrada.stream()
					.filter(libro -> libro.getTitulo().toLowerCase().contains(filtro.toLowerCase())
							|| libro.getEditorialDTO().getNombre().toLowerCase().contains(filtro.toLowerCase())
							|| libro.getIsbn().toLowerCase().contains(filtro.toLowerCase()))
					.collect(Collectors.toList());
		}
		
		// Aplicamos el orden si existe
		if (orden != null && !orden.isEmpty()) {
			switch (orden) {
			case "ascendente":
				listaLibrosFiltrada.sort(Comparator.comparing(LibroDTO::getTitulo));
				break;
			case "descendente":
				listaLibrosFiltrada.sort(Comparator.comparing(LibroDTO::getTitulo).reversed());
				break;
			case "editorial-ascendente":
				listaLibrosFiltrada.sort(Comparator.comparing(libro -> libro.getEditorialDTO().getNombre()));
				break;
			case "editorial-descendente":
				listaLibrosFiltrada.sort(
						Comparator.comparing(libro -> ((LibroDTO) libro).getEditorialDTO().getNombre()).reversed());
				break;
			}
		}
		
		int start = page * size;
		int end = Math.min(start + size, listaLibrosFiltrada.size());
		List<LibroDTO> subListaLibrosFiltrada = listaLibrosFiltrada.subList(start, end);
		
		ModelAndView mav = new ModelAndView("tienda");
		
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId(1L);
		// ESTA LINEA HAY QUE CAMBIAR EL "1" POR EL ID DEL USUARIO LOGEADO EN EL MOMENTO
		mav.addObject("usuario", usuario);
		mav.addObject("listaPopulares", listaAleatoria);
		
		mav.addObject("listaLibrosDTO", subListaLibrosFiltrada);
		mav.addObject("totalPages", (listaLibrosFiltrada.size() + size - 1) / size);
		mav.addObject("currentPage", page);
		
		return mav;
	}

}
