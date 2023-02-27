package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.SolicitudDTO;
import com.example.demo.model.dto.UsuarioDTO;

public interface SolicitudService {

	List<SolicitudDTO> findAll();

	List<SolicitudDTO> findByUsuario(UsuarioDTO usuarioDTO);

	void save(SolicitudDTO solicitudDTO);

	SolicitudDTO findById(SolicitudDTO solicitudDTO);

	void nuevaSolicitud(SolicitudDTO solicitudDTO);

}
