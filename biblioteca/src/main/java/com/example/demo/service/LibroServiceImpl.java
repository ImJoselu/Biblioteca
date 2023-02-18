package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.LibroDTO;
import com.example.demo.repository.dao.LibroRepository;
import com.example.demo.repository.entity.Libro;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	LibroRepository libroRepository;

	@Override
	public List<LibroDTO> findAll() {
		List<LibroDTO> listaDTONueva = new ArrayList<>();

		for (Libro libro : libroRepository.findAll()) {
			listaDTONueva.add(LibroDTO.convertToDTO(libro));
		}

		return listaDTONueva;
	}

	@Override
	public List<LibroDTO> findTop6ByOrderByIdDesc() {
		List<LibroDTO> listaDTONueva = new ArrayList<>();

		for (Libro libro : libroRepository.findTop6ByOrderByIdDesc()) {
			listaDTONueva.add(LibroDTO.convertToDTO(libro));
		}

		return listaDTONueva;
	}

}
