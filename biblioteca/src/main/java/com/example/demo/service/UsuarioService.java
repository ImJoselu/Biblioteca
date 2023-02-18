package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UsuarioDTO;

@Service
public interface UsuarioService {

	UsuarioDTO findById(Long idUsuario);

	List<UsuarioDTO> findAllClientes();

}
