package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.UsuarioDTO;

public interface UsuarioService {

	UsuarioDTO findById(Long idCliente);

	List<UsuarioDTO> findAllClientes();

}
