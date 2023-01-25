package com.example.demo.model.dto;

import java.io.Serializable;

import com.example.demo.repository.entity.Ejemplar;
import com.example.demo.repository.entity.Solicitud;

import lombok.Data;

@Data
public class EjemplarDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String localizacion;
	private Boolean prestado;
	
	// Convertir una entidad a un DTO
	public static EjemplarDTO convertToDTO(Ejemplar ejemplar) {

		EjemplarDTO ejemplarDTO = new EjemplarDTO();
		
		ejemplarDTO.setId(ejemplar.getId());
		ejemplarDTO.setLocalizacion(ejemplar.getLocalizacion());
		ejemplarDTO.setPrestado(ejemplar.getPrestado());


		return ejemplarDTO;
		// Cambio realizado por Cesar Rama DTO's
	}
	
	// Convertir una entidad a un DTO
	public static Ejemplar convertToEntity(EjemplarDTO ejemplarDTO) {

		Ejemplar ejemplar = new Ejemplar();
		
		ejemplar.setId(ejemplarDTO.getId());
		ejemplar.setLocalizacion(ejemplarDTO.getLocalizacion());
		ejemplar.setPrestado(ejemplarDTO.getPrestado());


		return ejemplar;
		// Cambio realizado por Cesar Rama DTO's
	}
}
