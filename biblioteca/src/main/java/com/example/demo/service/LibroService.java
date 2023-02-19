package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.LibroDTO;

public interface LibroService {

	List<LibroDTO> findAll();

	List<LibroDTO> findTop6ByOrderByIdDesc();

}
