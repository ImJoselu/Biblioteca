package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.LibroDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.entity.Ejemplar;

public interface AlquilerService {

	List<AlquilerDTO> findAllByUsuario(UsuarioDTO usuarioDTO);

	void save(AlquilerDTO alquilerDTO);

	EjemplarDTO alquilar(UsuarioDTO usuarioDTO, LibroDTO libroDTO);

}