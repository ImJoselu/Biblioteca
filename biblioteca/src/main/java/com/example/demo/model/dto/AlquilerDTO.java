package com.example.demo.model.dto;

import java.sql.Date;

import com.example.demo.repository.entity.Alquiler;

import lombok.Data;

@Data
public class AlquilerDTO {
	
	private Long id;
	private Date fecha_inicio;
	private Date fecha_limite;
	private Date fecha_entrega;
	

	// FALTAN LAS LISTAS Y CONSTRUCTOR VACIO

		public static AlquilerDTO convertToDTO(Alquiler alquiler) {
			AlquilerDTO alquilerDTO = new AlquilerDTO();
			alquilerDTO.setId(alquiler.getId());
			alquilerDTO.setFecha_inicio(alquiler.getFecha_inicio());
			alquilerDTO.setFecha_limite(alquiler.getFecha_limite());
			alquilerDTO.setFecha_entrega(alquiler.getFecha_entrega());

			return alquilerDTO;

		}

		public static Alquiler convertToEntity(AlquilerDTO alquilerDTO) {
			Alquiler alquiler = new Alquiler();
			alquiler.setId(alquilerDTO.getId());
			alquiler.setFecha_inicio(alquilerDTO.getFecha_inicio());
			alquiler.setFecha_limite(alquilerDTO.getFecha_limite());
			alquiler.setFecha_entrega(alquilerDTO.getFecha_entrega());

			return alquiler;

		}
	
}
