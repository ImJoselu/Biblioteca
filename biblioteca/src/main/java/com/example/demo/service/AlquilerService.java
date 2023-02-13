package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.UsuarioDTO;

@Service
public interface AlquilerService {
	
	List<AlquilerDTO> findAllByUsuario(UsuarioDTO usuarioDTO);

}