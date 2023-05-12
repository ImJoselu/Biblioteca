package com.example.demo.model.dto;

import java.io.Serializable;

import com.example.demo.repository.entity.Rol;
import com.example.demo.repository.entity.Usuario;

import lombok.Data;
import lombok.ToString;

@Data
public class RolDTO implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long id;
		private String nombre;

		@ToString.Exclude
		private UsuarioDTO usuarioDTO;
		
		public static RolDTO convertToDTO(Rol rol, UsuarioDTO usuarioDTO) {

			RolDTO rolDTO = new RolDTO();

			rolDTO.setId(rol.getId());
			rolDTO.setNombre(rol.getNombre());
			rolDTO.setUsuarioDTO(usuarioDTO);

			return rolDTO;
		}

		public static Rol convertToEntity(RolDTO rolDTO , Usuario usuario) {

			Rol rol = new Rol();

			rol.setId(rolDTO.getId());
			rol.setNombre(rolDTO.getNombre());
			rol.setUsuario(usuario);

			return rol;
		}
		
		public RolDTO() {
			super();
		}

	}
