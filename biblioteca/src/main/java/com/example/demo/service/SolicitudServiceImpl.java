package com.example.demo.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.SolicitudDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.EjemplarRepository;
import com.example.demo.repository.dao.SolicitudRepository;
import com.example.demo.repository.entity.Usuario;
import com.example.demo.repository.entity.Solicitud;
import com.example.demo.repository.entity.Solicitud;

@Service
public class SolicitudServiceImpl implements SolicitudService{

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(SolicitudDTO solicitudDTO) {
		// TODO Auto-generated method stub
		
	}

}
