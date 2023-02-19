package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Solicitud;
import com.example.demo.repository.entity.Usuario;

import lombok.Data;
import lombok.ToString;

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
	@ToString.Exclude
	private List<SolicitudDTO> listaSolicitudesDTO;
	@ToString.Exclude
	private List<AlquilerDTO> listaAlquileresDTO;

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

		SolicitudDTO solicitudDTO = new SolicitudDTO();
		AlquilerDTO alquilerDTO = new AlquilerDTO();
		/*
		 * for (Solicitud s : usuario.getListaSolicitudes()) { solicitudDTO =
		 * SolicitudDTO.convertToDTO(s);
		 * usuarioDTO.getListaSolicitudesDTO().add(solicitudDTO); }
		 * 
		 * for (Alquiler a : usuario.getListaAlquileres()) { alquilerDTO =
		 * AlquilerDTO.convertToDTO(a, usuarioDTO);
		 * usuarioDTO.getListaAlquileresDTO().add(alquilerDTO); <<<<<<< HEAD }
		 */

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

		Solicitud solicitud = new Solicitud();
		Alquiler alquiler = new Alquiler();

		for (SolicitudDTO s : usuarioDTO.getListaSolicitudesDTO()) {
			solicitud = SolicitudDTO.convertToEntity(s);
			usuario.getListaSolicitudes().add(solicitud);
		}

		for (AlquilerDTO a : usuarioDTO.getListaAlquileresDTO()) {
			alquiler = AlquilerDTO.convertToEntity(a);
			usuario.getListaAlquileres().add(alquiler);
		}

		return usuario;

	}

	public UsuarioDTO() {
		this.listaSolicitudesDTO = new ArrayList<SolicitudDTO>();
		this.listaAlquileresDTO = new ArrayList<AlquilerDTO>();
	}

}
