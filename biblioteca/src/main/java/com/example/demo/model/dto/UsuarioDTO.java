package com.example.demo.model.dto;

import java.io.Serializable;

import com.example.demo.repository.entity.Usuario;

import lombok.Data;


@Data
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nif;
	private String nombre;
	private String apellidos;
	private String email;
	private String username;
	private String password;
	private boolean es_administrador;
	private boolean es_cliente;

	// FALTAN LAS LISTAS Y CONSTRUCTOR VACIO

	public static UsuarioDTO convertToDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setNif(usuario.getNif());
		usuarioDTO.setNombre(usuario.getNombre());
		usuarioDTO.setApellidos(usuario.getApellidos());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setUsername(usuario.getUsername());
		usuarioDTO.setPassword(usuario.getPassword());
		usuarioDTO.setEs_administrador(usuario.isEs_administrador());
		usuarioDTO.setEs_cliente(usuario.isEs_cliente());

		return usuarioDTO;

	}

	public static Usuario convertToEntity(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDTO.getId());
		usuario.setNif(usuarioDTO.getNif());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setApellidos(usuarioDTO.getApellidos());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setUsername(usuarioDTO.getUsername());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setEs_administrador(usuarioDTO.isEs_administrador());
		usuario.setEs_cliente(usuarioDTO.isEs_cliente());

		return usuario;

	}
}
