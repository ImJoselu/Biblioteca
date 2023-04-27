package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.LibroDTO;

public interface EjemplarService {

	List<EjemplarDTO> findAll();

	List<EjemplarDTO> findByLibro(LibroDTO libroDTO);

}
