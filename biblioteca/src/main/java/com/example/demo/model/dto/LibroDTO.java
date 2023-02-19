package com.example.demo.model.dto;

/* FALTA genero y autor*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.repository.entity.Editorial;
import com.example.demo.repository.entity.Ejemplar;
import com.example.demo.repository.entity.Libro;
import com.example.demo.repository.entity.Solicitud;

import lombok.Data;
import lombok.ToString;

@Data
public class LibroDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String isbn;
	private String titulo;
	@ToString.Exclude
	private EditorialDTO editorialDTO;
	@ToString.Exclude
	private List<EjemplarDTO> listaEjemplaresDTO;

	// FALTAN LAS LISTAS Y CONSTRUCTOR VACIO

	public static LibroDTO convertToDTO(Libro libro) {
		LibroDTO libroDTO = new LibroDTO();
		libroDTO.setIsbn(libro.getIsbn());
		libroDTO.setTitulo(libro.getTitulo());
		libroDTO.setEditorialDTO(EditorialDTO.convertToDTO(libro.getEditorial()));

		/*
		 * EjemplarDTO ejemplarDTO = new EjemplarDTO(); for (Ejemplar e :
		 * libro.getListaEjemplares()) { ejemplarDTO = EjemplarDTO.convertToDTO(e);
		 * libroDTO.getListaEjemplaresDTO().add(ejemplarDTO);
		 * 
		 */

		return libroDTO;

	}

	public static Libro convertToEntity(LibroDTO libroDTO) {
		Libro libro = new Libro();
		libro.setIsbn(libroDTO.getIsbn());
		libro.setTitulo(libroDTO.getTitulo());
		libro.setEditorial(EditorialDTO.convertToEntity(libroDTO.getEditorialDTO()));

		Ejemplar ejemplar = new Ejemplar();
		for (EjemplarDTO e : libroDTO.getListaEjemplaresDTO()) {
			ejemplar = EjemplarDTO.convertToEntity(e);
			libro.getListaEjemplares().add(ejemplar);
		}

		return libro;
	}

	public LibroDTO() {
		this.editorialDTO = new EditorialDTO();
		this.listaEjemplaresDTO = new ArrayList<EjemplarDTO>();
	}

}