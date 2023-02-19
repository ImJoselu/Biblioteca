
package com.example.demo.model.dto;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Multa;
import com.example.demo.repository.entity.Solicitud;

import lombok.Data;
import lombok.ToString;

@Data
public class AlquilerDTO {

	private Long id;
	private Date fecha_inicio;
	private Date fecha_limite;
	private Date fecha_entrega;
	@ToString.Exclude
	private List<MultaDTO> listaMultasDTO;
	@ToString.Exclude
	private UsuarioDTO usuarioDTO;
	@ToString.Exclude
	private EjemplarDTO ejemplarDTO;

	// FALTAN LAS LISTAS Y CONSTRUCTOR VACIO

	public static AlquilerDTO convertToDTO(Alquiler alquiler, UsuarioDTO usuarioDTO) {
		AlquilerDTO alquilerDTO = new AlquilerDTO();
		alquilerDTO.setId(alquiler.getId());
		alquilerDTO.setFecha_inicio(alquiler.getFecha_inicio());
		alquilerDTO.setFecha_limite(alquiler.getFecha_limite());
		alquilerDTO.setFecha_entrega(alquiler.getFecha_entrega());
		alquilerDTO.setUsuarioDTO(usuarioDTO);
		alquilerDTO.setEjemplarDTO(EjemplarDTO.convertToDTO(alquiler.getEjemplar()));
		/*
		 * 
		 * MultaDTO multaDTO = new MultaDTO();
		 * 
		 * for (Multa m : alquiler.getListaMultas()) { multaDTO =
		 * MultaDTO.convertToDTO(m, alquilerDTO);
		 * alquilerDTO.getListaMultasDTO().add(multaDTO); }
		 */

		return alquilerDTO;

	}

	public static Alquiler convertToEntity(AlquilerDTO alquilerDTO) {
		Alquiler alquiler = new Alquiler();
		alquiler.setId(alquilerDTO.getId());
		alquiler.setFecha_inicio(alquilerDTO.getFecha_inicio());
		alquiler.setFecha_limite(alquilerDTO.getFecha_limite());
		alquiler.setFecha_entrega(alquilerDTO.getFecha_entrega());
		alquiler.setUsuario(UsuarioDTO.convertToEntity(alquilerDTO.getUsuarioDTO()));
		alquiler.setEjemplar(EjemplarDTO.convertToEntity(alquilerDTO.getEjemplarDTO()));

		Multa multa = new Multa();

		for (MultaDTO m : alquilerDTO.getListaMultasDTO()) {
			multa = MultaDTO.convertToEntity(m);
			alquiler.getListaMultas().add(multa);
		}

		return alquiler;

	}

}