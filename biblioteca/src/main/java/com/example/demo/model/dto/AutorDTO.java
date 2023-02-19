package com.example.demo.model.dto;

/* FALTA N - N */
import java.io.Serializable;

import com.example.demo.repository.entity.Autor;

import lombok.Data;

@Data
public class AutorDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String apellidos;

	// FALTAN LAS LISTAS Y CONSTRUCTOR VACIO

	public static AutorDTO convertToDTO(Autor autor) {
		AutorDTO autorDTO = new AutorDTO();
		autorDTO.setId(autor.getId());
		autorDTO.setNombre(autor.getNombre());
		autorDTO.setApellidos(autor.getApellidos());

		return autorDTO;

	}

	public static Autor convertToEntity(AutorDTO autorDTO) {
		Autor autor = new Autor();
		autor.setId(autorDTO.getId());
		autor.setNombre(autorDTO.getNombre());
		autor.setApellidos(autor.getApellidos());

		return autor;

	}
}
