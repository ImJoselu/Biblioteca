package com.example.demo.model.dto;

import java.io.Serializable;

import com.example.demo.repository.entity.Multa;
import com.example.demo.repository.entity.Solicitud;
import com.example.demo.repository.entity.Usuario;

import ch.qos.logback.core.status.StatusUtil;
import lombok.Data;
import lombok.ToString;

@Data
public class SolicitudDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

	private String username;
	private String email;
	private String ISBN;
	private String titulo;
	private String mensaje;
	private String estado;
	@ToString.Exclude
	private UsuarioDTO usuarioDTO;

	// Convertir una entidad a un DTO
	public static SolicitudDTO convertToDTO(Solicitud solicitud) {

		SolicitudDTO solicitudDTO = new SolicitudDTO();
		solicitudDTO.setId(solicitud.getId());
		solicitudDTO.setUsername(solicitud.getUsername());
		solicitudDTO.setEmail(solicitud.getEmail());
		solicitudDTO.setISBN(solicitud.getISBN());
		solicitudDTO.setTitulo(solicitud.getTitulo());
		solicitudDTO.setMensaje(solicitud.getMensaje());
		solicitudDTO.setEstado(solicitud.getEstado());

		solicitudDTO.setUsuarioDTO(UsuarioDTO.convertToDTO(solicitud.getUsuario()));

		return solicitudDTO;

	}

	// Convertir una entidad a un DTO
	public static Solicitud convertToEntity(SolicitudDTO solicitudDTO) {

		Solicitud solicitud = new Solicitud();
		solicitud.setId(solicitudDTO.getId());
		solicitud.setUsername(solicitudDTO.getUsername());
		solicitud.setEmail(solicitudDTO.getEmail());
		solicitud.setISBN(solicitudDTO.getISBN());
		solicitud.setTitulo(solicitudDTO.getTitulo());
		solicitud.setMensaje(solicitudDTO.getMensaje());
		solicitud.setEstado(solicitudDTO.getEstado());

		solicitud.setUsuario(UsuarioDTO.convertToEntity(solicitudDTO.getUsuarioDTO()));

		return solicitud;
	}

	public SolicitudDTO() {
		this.usuarioDTO = new UsuarioDTO();
		;
	}

}
