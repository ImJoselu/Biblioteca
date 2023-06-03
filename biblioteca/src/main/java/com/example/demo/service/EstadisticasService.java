package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.EstadisticaDTO;
import com.example.demo.model.dto.LibroDTO;
import com.example.demo.repository.entity.Libro;

public interface EstadisticasService {

	EstadisticaDTO generosPopulares();

	EstadisticaDTO librosPopulares();

	EstadisticaDTO alquileresPorMes(int anyo);
	
	public List<LibroDTO> librosRecomendados();
}