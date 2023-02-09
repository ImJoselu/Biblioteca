package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.repository.dao.AlquilerRepository;

public class AlquilerServiceImpl implements AlquilerService{

private static final Logger log = LoggerFactory.getLogger(AlquilerServiceImpl.class);
	
	@Autowired
	private AlquilerRepository alquilerRepository;
	
	
	@Override
	public List<AlquilerDTO> findAll() {
		log.info("AlquilerServiceImpl - findAll: Lista de todos los cliente");

		List<AlquilerDTO> listaAlquileresDTO = alquilerRepository.findAll()
				.stream()
				.map(p->AlquilerDTO.convertToDTO(p))
				.collect(Collectors.toList());
		
		return listaAlquileresDTO;
	}

}
