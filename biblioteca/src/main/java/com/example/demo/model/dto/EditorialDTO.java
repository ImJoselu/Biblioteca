package com.example.demo.model.dto;

import java.io.Serializable;

import com.example.demo.repository.entity.Editorial;
import com.example.demo.repository.entity.Ejemplar;

import lombok.Data;

@Data
public class EditorialDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigo_editorial;
	private String nombre;
	private int numero_contacto;
	
	// Convertir una entidad a un DTO
		public static EditorialDTO convertToDTO(Editorial editorial) {

			EditorialDTO editorialDTO = new EditorialDTO();
			
			editorialDTO.setCodigo_editorial(editorial.getCodigo_editorial());
			editorialDTO.setNombre(editorial.getNombre());
			editorialDTO.setNumero_contacto(editorial.getNumero_contacto());


			return editorialDTO;
			// Cambio realizado por Cesar Rama DTO's
		}
		
		// Convertir una entidad a un DTO
		public static Editorial convertToEntity(EditorialDTO editorialDTO) {

			Editorial editorial = new Editorial();
			
			editorial.setCodigo_editorial(editorialDTO.getCodigo_editorial());
			editorial.setNombre(editorialDTO.getNombre());
			editorial.setNumero_contacto(editorialDTO.getNumero_contacto());

			return editorial;
			// Cambio realizado por Cesar Rama DTO's
		}
}
