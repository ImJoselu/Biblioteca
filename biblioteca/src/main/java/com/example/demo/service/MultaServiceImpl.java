package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.dto.MultaDTO;
import com.example.demo.repository.dao.MultaRepository;

public class MultaServiceImpl implements MultaService{

private static final Logger log = LoggerFactory.getLogger(MultaServiceImpl.class);
	
	@Autowired
	private MultaRepository multaRepository;
	
	
	@Override
	public List<MultaDTO> findAll() {
		log.info("MultaServiceImpl - findAll: Lista de todos los multas");

		List<MultaDTO> listaMultasDTO = multaRepository.findAll()
				.stream()
				.map(p->MultaDTO.convertToDTO(p))
				.collect(Collectors.toList());
		
		return listaMultasDTO;
	}
}
