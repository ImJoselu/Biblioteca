package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.configuracion.EncriptaPassword;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.UsuarioRepository;
import com.example.demo.repository.entity.Rol;
import com.example.demo.repository.entity.Usuario;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService , UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UsuarioDTO findById(Long idUsuario) {
		log.info("UsuarioServiceImpl - findById: Busca el usuario: " + idUsuario);
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

	@Override
	public void save(UsuarioDTO usuarioDTO) {
		log.info("UsuarioServiceImpl - save: Salva el cliente: " + usuarioDTO.toString());
		Usuario usuario = UsuarioDTO.convertToEntity(usuarioDTO);
		usuario.setPassword(EncriptaPassword.encriptarPassword(usuarioDTO.getPassword()));
		usuarioRepository.actualizarUsuario(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("UsuarioServiceImpl - loadUserByUsername: " + username);

		Usuario usuario = usuarioRepository.findByUsername(username);
		if (usuario != null) {
			List<GrantedAuthority> listaPermisos = new ArrayList<GrantedAuthority>();
			List<Rol> listaRoles = new ArrayList<Rol>(usuario.getListaRoles());
			for (Rol rol : listaRoles) {
				listaPermisos.add(new SimpleGrantedAuthority(rol.getNombre()));
			}

			return new User(usuario.getUsername(), usuario.getPassword(), listaPermisos);

		} else {
			throw new UsernameNotFoundException(username);
		}
	}

}
