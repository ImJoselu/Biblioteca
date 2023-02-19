package com.example.demo.model.dto;

import java.io.Serializable;

import com.example.demo.repository.entity.Genero;
import lombok.Data;

@Data
public class GeneroDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;

	// FALTAN LAS LISTAS Y CONSTRUCTOR VACIO

	public static GeneroDTO convertToDTO(Genero genero) {
		GeneroDTO generoDTO = new GeneroDTO();
		generoDTO.setId(genero.getId());
		generoDTO.setNombre(genero.getNombre());

		return generoDTO;
	}

	public static Genero convertToEntity(GeneroDTO generoDTO) {
		Genero genero = new Genero();
		genero.setId(generoDTO.getId());
		genero.setNombre(generoDTO.getNombre());

		return genero;
	}

}
