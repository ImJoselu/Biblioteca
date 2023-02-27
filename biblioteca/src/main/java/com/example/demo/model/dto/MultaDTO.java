package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.example.demo.repository.entity.Multa;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

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
	@ToString.Exclude
	private AlquilerDTO alquilerDTO;

	// Convertir una entidad a un DTO
	public static MultaDTO convertToDTO(Multa multa, AlquilerDTO alquilerDTO) {

		MultaDTO multaDTO = new MultaDTO();
		multaDTO.setId(multa.getId());
		multaDTO.setFecha(multa.getFecha());
		multaDTO.setDescartada(multa.getDescartada());
		multaDTO.setImporte(multa.getImporte());
		multaDTO.setObservaciones(multa.getObservaciones());

		multaDTO.setAlquilerDTO(alquilerDTO);

		return multaDTO;

	}

	// Convertir una entidad a un DTO
	public static Multa convertToEntity(MultaDTO multaDTO) {

		Multa multa = new Multa();
		multa.setId(multaDTO.getId());
		multa.setFecha(multaDTO.getFecha());
		multa.setDescartada(multaDTO.getDescartada());
		multa.setImporte(multaDTO.getImporte());
		multa.setObservaciones(multaDTO.getObservaciones());

		//multa.setAlquiler(AlquilerDTO.convertToEntity(multaDTO.getAlquilerDTO()));
		
		return multa;

	}

	public MultaDTO() {
		this.alquilerDTO = new AlquilerDTO();
	}

}