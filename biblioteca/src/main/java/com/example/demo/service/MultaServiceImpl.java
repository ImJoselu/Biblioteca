package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.MultaDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.model.dto.MultaDTO;
import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.MultaDTO;
import com.example.demo.repository.dao.MultaRepository;
import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Multa;
import com.example.demo.repository.entity.Multa;

@Service
public class MultaServiceImpl implements MultaService {

	private static final Logger log = LoggerFactory.getLogger(MultaServiceImpl.class);

	@Autowired
	private MultaRepository multaRepository;

	@Override
	public List<MultaDTO> findAllByAlquiler(AlquilerDTO alquilerDTO) {
		log.info("MultaServiceImpl - finfindAllByAlquilerAll: Lista de todas las multas del alquiler: "
				+ alquilerDTO.getId());

		// Obtenemos la listaMultas de multas del alquiler
		List<Multa> listaMultas = (List<Multa>) multaRepository.findAllByAlquiler(alquilerDTO.getId());
		// Creamos una listaMultas de MultaDTO que serÃ¡ la que devolvamos al
		// controlador
		List<MultaDTO> listaMultasDTO = new ArrayList<MultaDTO>();
		// Recorremos la listaMultas de multas y las mapeamos a DTO
		for (int i = 0; i < listaMultas.size(); ++i) {
			listaMultasDTO.add(MultaDTO.convertToDTO(listaMultas.get(i), alquilerDTO));
		}
		// Devolvemos la listaMultas de DTO's
		return listaMultasDTO;
	}

	@Override
	public void save(MultaDTO multaDTO) {
		log.info("MultaServiceImpl - save: salvamos la multa : " + multaDTO.toString());

		Multa multa = MultaDTO.convertToEntity(multaDTO);
		// Seguimos sin tener la necesidad de buscarlo
		Alquiler alquiler = new Alquiler();
		alquiler.setId(multaDTO.getAlquilerDTO().getId());
		multa.setAlquiler(alquiler);

		Date fecha = new Date();
		multa.setFecha(fecha);
		
		multa.setDescartada(false);
 
		multaRepository.save(multa);

	}

	@Override
	public void delete(MultaDTO multaDTO) {
		log.info("MultaServiceImpl - delete: Metodo 1: borramos la multa: " + multaDTO.toString());

		multaRepository.deleteById(multaDTO.getId());
	}

	@Override
	public List<MultaDTO> findAllByUsuario(UsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		List<Multa> listaMultas = multaRepository.findAllByCliente(usuarioDTO.getId());
		List<MultaDTO> listaMultasDTO = new ArrayList<>();

		for (Multa multa : listaMultas) {
			MultaDTO multaDTO = MultaDTO.convertToDTO(multa, new AlquilerDTO());
			listaMultasDTO.add(multaDTO);
		}

		return listaMultasDTO;
	}

	@Override
	public void descartar(MultaDTO multaDTO) {
		// TODO Auto-generated method stub
		Optional<Multa> multa = multaRepository.findById(multaDTO.getId());
		multa.get().setDescartada(true);
		
		multaRepository.save(multa.get());
		
	}
}