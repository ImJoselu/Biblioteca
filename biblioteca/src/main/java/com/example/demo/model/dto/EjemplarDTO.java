package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.List;

import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Ejemplar;
import com.example.demo.repository.entity.Solicitud;

import lombok.Data;
import lombok.ToString;

@Data
public class EjemplarDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String localizacion;
	private Boolean prestado;
	@ToString.Exclude
	private LibroDTO libroDTO;
	@ToString.Exclude
	private List<AlquilerDTO> listaAlquileresDTO;

	// Convertir una entidad a un DTO
	public static EjemplarDTO convertToDTO(Ejemplar ejemplar) {

		EjemplarDTO ejemplarDTO = new EjemplarDTO();

		ejemplarDTO.setId(ejemplar.getId());
		ejemplarDTO.setLocalizacion(ejemplar.getLocalizacion());
		ejemplarDTO.setPrestado(ejemplar.getPrestado());
		ejemplarDTO.setLibroDTO(LibroDTO.convertToDTO(ejemplar.getLibro()));

		AlquilerDTO alquilerDTO = new AlquilerDTO();

		/*
		 * for (Alquiler a : ejemplar.getListaAlquileres()) { alquilerDTO =
		 * AlquilerDTO.convertToDTO(a, null);
		 * ejemplarDTO.getListaAlquileresDTO().add(alquilerDTO); }
		 */

		return ejemplarDTO;
	}

	// Convertir una entidad a un DTO
	public static Ejemplar convertToEntity(EjemplarDTO ejemplarDTO) {

		Ejemplar ejemplar = new Ejemplar();

		ejemplar.setId(ejemplarDTO.getId());
		ejemplar.setLocalizacion(ejemplarDTO.getLocalizacion());
		ejemplar.setPrestado(ejemplarDTO.getPrestado());
		ejemplar.setLibro(LibroDTO.convertToEntity(ejemplarDTO.getLibroDTO()));

		Alquiler alquiler = new Alquiler();
		for (AlquilerDTO a : ejemplarDTO.getListaAlquileresDTO()) {
			alquiler = AlquilerDTO.convertToEntity(a);
			ejemplar.getListaAlquileres().add(alquiler);
		}

		return ejemplar;
	}
}
