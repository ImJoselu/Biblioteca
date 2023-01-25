package com.example.demo.model.dto;

import java.io.Serializable;
import java.sql.Date;

import com.example.demo.repository.entity.Multa;

import lombok.Data;

@Data
public class MultaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date fecha;
	private Boolean descartada;
	private Double importe;
	private String observaciones;

	// Convertir una entidad a un DTO
	public static MultaDTO convertToDTO(Multa multa) {

		MultaDTO multaDTO = new MultaDTO();
		multaDTO.setId(multa.getId());
		multaDTO.setFecha(multa.getFecha());
		multaDTO.setDescartada(multa.getDescartada());
		multaDTO.setImporte(multa.getImporte());
		multaDTO.setObservaciones(multa.getObservaciones());

		return multaDTO;
		// Cambio realizado por Cesar Rama DTO's
	}
	
	// Convertir una entidad a un DTO
	public static Multa convertToEntity(MultaDTO multaDTO) {

		Multa multa = new Multa();
		multa.setId(multaDTO.getId());
		multa.setFecha(multaDTO.getFecha());
		multa.setDescartada(multaDTO.getDescartada());
		multa.setImporte(multaDTO.getImporte());
		multa.setObservaciones(multaDTO.getObservaciones());

		return multa;
		// Cambio realizado por Cesar Rama DTO's
	}
}