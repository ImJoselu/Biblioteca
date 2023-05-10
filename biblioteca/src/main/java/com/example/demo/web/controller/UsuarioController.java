package com.example.demo.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.configuracion.EncriptaPassword;
import com.example.demo.model.dto.RolDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@GetMapping("/adminClientes")
	public ModelAndView adminClientes() {

		log.info("UsuarioController - index: Mostramos la gestion de clientes");

		List<UsuarioDTO> listaClientesDTO = usuarioService.findAllClientes();

		ModelAndView mav = new ModelAndView("adminClientes");
		mav.addObject("listaClientesDTO", listaClientesDTO);

		return mav;
	}

	@GetMapping("/usuario/{idUsuario}/adminEditarUsuarios")
	public ModelAndView adminEditarUsuarios(@PathVariable Long idUsuario) {

		log.info("UsuarioController - adminEditarUsuarios: editamos la gestion de usuarios");
		ModelAndView mav = new ModelAndView("adminEditarUsuarios");
		UsuarioDTO usuarioDTO = usuarioService.findById(idUsuario);
		log.info(usuarioDTO.toString());

		mav.addObject("usuarioDTO", usuarioDTO);

		return mav;
	}

	// Salvar
	@PostMapping("/usuarios/save")
	public ModelAndView save(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, @RequestParam String[] roles) {

		log.info("UsuarioController - save: Salvamos los datos del usuario:" + usuarioDTO.toString());
		for (String param : roles) {
			RolDTO rolDTO = new RolDTO();
			rolDTO.setNombre(param);
			rolDTO.setUsuarioDTO(usuarioDTO);
			usuarioDTO.getListaRolesDTO().add(rolDTO);
		}
		// Invocamos a la capa de servicios para que almacene los datos del usuario
		usuarioService.save(usuarioDTO);
		// Redireccionamos para volver a invocar a la raiz
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}
	
	// Cuenta
	@GetMapping("/usuarios/{usernameUsuario}")
	public ModelAndView findById(@PathVariable("usernameUsuario") String usernameUsuario) {
				
		log.info("UsuarioController - adminEditarUsuarios: editamos la gestion de usuarios");
		ModelAndView mav = new ModelAndView("cuenta");
		UsuarioDTO usuarioDTO = usuarioService.findByName(usernameUsuario);
		log.info(usuarioDTO.toString());

		mav.addObject("usuarioDTO", usuarioDTO);

		return mav;
	}
}
