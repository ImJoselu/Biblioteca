package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.UsuarioRepository;
import com.example.demo.repository.entity.Usuario;


@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UsuarioDTO findById(Long idCliente) {
		// TODO Auto-generated method stub
		return null;
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
