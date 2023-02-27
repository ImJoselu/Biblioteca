package com.example.demo.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.SolicitudDTO;
import com.example.demo.model.dto.SolicitudDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.EjemplarRepository;
import com.example.demo.repository.dao.SolicitudRepository;
import com.example.demo.repository.entity.Usuario;
import com.example.demo.repository.entity.Solicitud;
import com.example.demo.repository.entity.Solicitud;
import com.example.demo.repository.entity.Solicitud;

@Service
public class SolicitudServiceImpl implements SolicitudService {

	private static final Logger log = LoggerFactory.getLogger(SolicitudServiceImpl.class);

	@Autowired
	SolicitudRepository solicitudRepository;

	@Override
	public List<SolicitudDTO> findAll() {
		// TODO Auto-generated method stub
		List<Solicitud> listaSolicitudes = solicitudRepository.findAll();

		List<SolicitudDTO> listaSolicitudesDTO = new ArrayList<>();

		for (Solicitud solicitud : listaSolicitudes) {
			SolicitudDTO solicitudDTO = SolicitudDTO.convertToDTO(solicitud);
			listaSolicitudesDTO.add(solicitudDTO);

		}

		return listaSolicitudesDTO;
	}

	@Override
	public List<SolicitudDTO> findByUsuario(UsuarioDTO usuarioDTO) {
		log.info("SolicitudServiceImpl - findAllByUsuario: Mostramos todas las solicituds del usuario "
				+ usuarioDTO.toString());

		List<Solicitud> listaSolicitudes = solicitudRepository.findAllByUsuario(usuarioDTO.getId());

		List<SolicitudDTO> listaSolicitudesDTO = new ArrayList<SolicitudDTO>();
		for (int i = 0; i < listaSolicitudes.size(); i++) {
			listaSolicitudesDTO.add(SolicitudDTO.convertToDTO(listaSolicitudes.get(i)));
		}

		return listaSolicitudesDTO;
	}

	@Override
	public void save(SolicitudDTO solicitudDTO) {
		// TODO Auto-generated method stub
		log.info("SolicitudServiceImpl - findById: Entidad usuario: ");
		Solicitud solicitud = SolicitudDTO.convertToEntity(solicitudDTO);

		solicitudRepository.updateEstadoById(solicitud.getId(), solicitud.getEstado());

	}
	
	@Override
	public void nuevaSolicitud(SolicitudDTO solicitudDTO) {
		// TODO Auto-generated method stub
		log.info("SolicitudServiceImpl - findById: Entidad usuario: ");
		Solicitud solicitud = SolicitudDTO.convertToEntity(solicitudDTO);
		
		solicitud.setEstado("Pendiente");

		solicitudRepository.save(solicitud);

	}

	@Override
	public SolicitudDTO findById(SolicitudDTO solicitudDTO) {
		// TODO Auto-generated method stub

		return SolicitudDTO.convertToDTO(solicitudRepository.findById(solicitudDTO.getId()).get());
	}

}
