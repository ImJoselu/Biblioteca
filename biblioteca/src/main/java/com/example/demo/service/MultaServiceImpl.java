package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.dto.MultaDTO;
import com.example.demo.model.dto.MultaDTO;
import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.repository.dao.MultaRepository;
import com.example.demo.repository.entity.Multa;

public class MultaServiceImpl implements MultaService {

	private static final Logger log = LoggerFactory.getLogger(MultaServiceImpl.class);

	@Autowired
	private MultaRepository multaRepository;

	@Override
	public List<MultaDTO> findAllByAlquiler(AlquilerDTO alquilerDTO) {
		log.info("MultaServiceImpl - finfindAllByAlquilerAll: Lista de todas las multas del alquiler: "
				+ alquilerDTO.getId());

		// Obtenemos la listaMultas de multas del alquiler
		List<Multa> listaMultas = (List<Multa>) multaRepository.findAllByAlquiler(alquilerDTO.getId());
		// Creamos una listaMultas de MultaDTO que serÃ¡ la que devolvamos al controlador
		List<MultaDTO> listaMultasDTO = new ArrayList<MultaDTO>();
		// Recorremos la listaMultas de multas y las mapeamos a DTO
		for (int i = 0; i < listaMultas.size(); ++i) {
			listaMultasDTO.add(MultaDTO.convertToDTO(listaMultas.get(i), alquilerDTO));
		}
		// Devolvemos la listaMultas de DTO's
		return listaMultasDTO;
	}
}