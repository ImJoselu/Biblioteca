
package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.AlquilerRepository;
import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Usuario;
import com.example.demo.repository.entity.Alquiler;

public class AlquilerServiceImpl implements AlquilerService{

private static final Logger log = LoggerFactory.getLogger(AlquilerServiceImpl.class);
	
	@Autowired
	private AlquilerRepository alquilerRepository;

	@Override
	public List<AlquilerDTO> findAllByUsuario(UsuarioDTO usuarioDTO) {
		log.info("AlquilerServiceImpl - findAllByUsuario: Lista de todas las alquilers del usuario: "
				+ usuarioDTO.getId());

		// Obtenemos la lista de alquilers del usuario
		List<Alquiler> listaAlquileres = (List<Alquiler>) alquilerRepository.findAllByUsuario(usuarioDTO.getId());
		// Creamos una lista de AlquilerDTO que serÃ¡ la que devolvamos al controlador
		List<AlquilerDTO> listaAlquileresDTO = new ArrayList<AlquilerDTO>();
		// Recorremos la lista de alquilers y las mapeamos a DTO
		for (int i = 0; i < listaAlquileres.size(); ++i) {
			listaAlquileresDTO.add(AlquilerDTO.convertToDTO(listaAlquileres.get(i), usuarioDTO));
		}
		// Devolvemos la lista de DTO's
		return listaAlquileresDTO;
	}

	@Override
	public void save(AlquilerDTO alquilerDTO) {
		log.info("AlquilerServiceImpl - save: salvamos la alquiler : " + alquilerDTO.toString());

		Alquiler alquiler = AlquilerDTO.convertToEntity(alquilerDTO);
		// Seguimos sin tener la necesidad de buscarlo
		Usuario usuario = new Usuario();
		usuario.setId(alquilerDTO.getUsuarioDTO().getId());
		alquiler.setUsuario(usuario);
 
		alquilerRepository.save(alquiler);

		
	}

}