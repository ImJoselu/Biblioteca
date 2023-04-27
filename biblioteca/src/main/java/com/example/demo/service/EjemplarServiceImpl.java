package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.LibroDTO;
import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.repository.dao.EjemplarRepository;
import com.example.demo.repository.entity.Ejemplar;
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

	@Override
	public List<EjemplarDTO> findByLibro(LibroDTO libroDTO) {
		// Obtenemos la listaEjemplars de ejemplars del libro
		List<Ejemplar> listaEjemplares = (List<Ejemplar>) ejemplarRepository.findAllByLibro(libroDTO.getIsbn());
		// Creamos una listaEjemplars de EjemplarDTO que serÃ¡ la que devolvamos al
		// controlador
		List<EjemplarDTO> listaEjemplaresDTO = new ArrayList<EjemplarDTO>();
		// Recorremos la listaEjemplars de ejemplars y las mapeamos a DTO
		for (int i = 0; i < listaEjemplares.size(); ++i) {
			listaEjemplaresDTO.add(EjemplarDTO.convertToDTO(listaEjemplares.get(i)));
		}
		// Devolvemos la listaEjemplars de DTO's
		return listaEjemplaresDTO;
	}

}
