package com.example.demo.web.RESTcontroller;

import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.EstadisticaDTO;
import com.example.demo.service.EstadisticasService;

@RestController
@RequestMapping("/ws/estadisticas")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST}, allowedHeaders = {"*"})
public class EstadisticasController {

	@Autowired
	EstadisticasService estadisticasService;
	
	@GetMapping("/generosPopulares")
	public ResponseEntity<EstadisticaDTO> generosPopulares() {
	//Numero de veces que un genero ha sido alquilado
	//List<ClienteDTO> listaClientesDTO = clienteService.findAll();
	
	EstadisticaDTO stat = estadisticasService.generosPopulares();

	return new ResponseEntity<>(stat, HttpStatus.OK);
	}
	
	@GetMapping("/librosPopulares")
	public ResponseEntity<EstadisticaDTO> librosPopulares() {
	//Numero de veces que un genero ha sido alquilado
	//List<ClienteDTO> listaClientesDTO = clienteService.findAll();
	
	EstadisticaDTO stat = estadisticasService.librosPopulares();

	return new ResponseEntity<>(stat, HttpStatus.OK);
	}
	
	@GetMapping("/alquiladosPorMes")
	public ResponseEntity<EstadisticaDTO> alquiladosPorMes(@RequestParam(name="anyo", defaultValue = "#{T(java.time.Year).now().getValue()}") int año) {
	//Numero de veces que un genero ha sido alquilado
	//List<ClienteDTO> listaClientesDTO = clienteService.findAll();
	
	EstadisticaDTO stat = estadisticasService.alquileresPorMes(año);

	return new ResponseEntity<>(stat, HttpStatus.OK);
	}
}
