package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UsuarioDTO;

@Service
public interface UsuarioService {

	UsuarioDTO findById(Long idUsuario);

	List<UsuarioDTO> findAllClientes();

	void save(UsuarioDTO usuarioDTO);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	UsuarioDTO findByName(String usernameUsuario);

	void saveNuevoUsuario(UsuarioDTO usuarioDTO);

	UsuarioDTO cambiarPremium(UsuarioDTO usuarioDTO);

	UsuarioDTO cambiarEstandar(UsuarioDTO usuarioDTO);
}
