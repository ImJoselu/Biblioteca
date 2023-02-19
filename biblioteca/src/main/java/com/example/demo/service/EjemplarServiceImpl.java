package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.repository.dao.EjemplarRepository;
import com.example.demo.repository.entity.Ejemplar;

@Service
public class EjemplarServiceImpl implements EjemplarService {

	@Autowired
	EjemplarRepository ejemplarRepository;

	@Override
	public List<EjemplarDTO> findAll() {
		// TODO Auto-generated method stub
		List<Ejemplar> listaEjemplares = ejemplarRepository.findAll();

		List<EjemplarDTO> listaEjemplaresDTO = new ArrayList<>();

		for (Ejemplar ejemplar : listaEjemplares) {
			EjemplarDTO ejemplarDTO = EjemplarDTO.convertToDTO(ejemplar);
			listaEjemplaresDTO.add(ejemplarDTO);

		}

		return listaEjemplaresDTO;

	}

}
