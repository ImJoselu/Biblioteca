package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.MultaDTO;
import com.example.demo.model.dto.UsuarioDTO;

@Service
public interface MultaService {

	List<MultaDTO> findAllByAlquiler(AlquilerDTO alquilerDTO);

	public void save(MultaDTO multaDTO);

	public void delete(MultaDTO multaDTO);

	List<MultaDTO> findAllByUsuario(UsuarioDTO usuarioDTO);
	void descartar(MultaDTO multaDTO);

}
