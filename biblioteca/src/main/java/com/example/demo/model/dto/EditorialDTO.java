package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.List;

import com.example.demo.repository.entity.Editorial;
import com.example.demo.repository.entity.Libro;

import lombok.Data;
import lombok.ToString;

@Data
public class EditorialDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigo_editorial;
	private String nombre;
	private int numero_contacto;
	@ToString.Exclude
	private List<LibroDTO> listaLibrosDTO;

	// Convertir una entidad a un DTO
	public static EditorialDTO convertToDTO(Editorial editorial) {

		EditorialDTO editorialDTO = new EditorialDTO();
		editorialDTO.setCodigo_editorial(editorial.getCodigo_editorial());
		editorialDTO.setNombre(editorial.getNombre());
		editorialDTO.setNumero_contacto(editorial.getNumero_contacto());

		LibroDTO libroDTO = new LibroDTO();

		/*
		 * for (Libro l : editorial.getListaLibros()) { libroDTO =
		 * LibroDTO.convertToDTO(l); editorialDTO.getListaLibrosDTO().add(libroDTO); }
		 */

		return editorialDTO;
	}

	// Convertir una entidad a un DTO
	public static Editorial convertToEntity(EditorialDTO editorialDTO) {

		Editorial editorial = new Editorial();
		editorial.setCodigo_editorial(editorialDTO.getCodigo_editorial());
		editorial.setNombre(editorialDTO.getNombre());
		editorial.setNumero_contacto(editorialDTO.getNumero_contacto());

		Libro libro = new Libro();

		for (LibroDTO l : editorialDTO.getListaLibrosDTO()) {
			libro = LibroDTO.convertToEntity(l);
			editorial.getListaLibros().add(libro);
		}
		return editorial;
	}
}
