package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.UsuarioRepository;
import com.example.demo.repository.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UsuarioDTO findById(Long idUsuario) {
		log.info("ClienteServiceImpl - findById: Busca el usuario: " + idUsuario);
		// Paso de DTO a entidad
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		// Paso de entidad a DTO
		usuarioDTO = UsuarioDTO.convertToDTO(usuario.get());

		return usuarioDTO;

	}

	@Override
	public List<UsuarioDTO> findAllClientes() {
		// TODO Auto-generated method stub
		List<Usuario> listaClientes = usuarioRepository.findAllClientes();

		List<UsuarioDTO> listaClientesDTO = new ArrayList<>();

		for (Usuario usuario : listaClientes) {
			UsuarioDTO usuarioDTO = UsuarioDTO.convertToDTO(usuario);
			listaClientesDTO.add(usuarioDTO);
		}
		return listaClientesDTO;
	}

}
