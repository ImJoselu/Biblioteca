package com.example.demo.model.dto;

import java.io.Serializable;

import com.example.demo.repository.entity.Libro;

import lombok.Data;

@Data
public class LibroDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String isbn;
	private String titulo;

	// FALTAN LAS LISTAS Y CONSTRUCTOR VACIO

	public static LibroDTO convertToDTO(Libro libro) {
		LibroDTO libroDTO = new LibroDTO();
		libroDTO.setIsbn(libro.getIsbn());
		libroDTO.setTitulo(libro.getTitulo());

		return libroDTO;

	}

	public static Libro convertToEntity(LibroDTO libroDTO) {
		Libro libro = new Libro();
		libro.setIsbn(libroDTO.getIsbn());
		libro.setTitulo(libroDTO.getTitulo());

		return libro;
	}
}