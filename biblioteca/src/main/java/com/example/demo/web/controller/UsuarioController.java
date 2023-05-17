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

	@PostMapping("/usuarios/saveAdmin")
	public ModelAndView saveAdmin(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO) {

		log.info("UsuarioController - save:Salvamos los datos del usuarioDTO");
		log.info("UsuarioController - save:Salvamos los datos del usuarioDTO: " + usuarioDTO.toString());

		ModelAndView mav = new ModelAndView();
		boolean seguir = !usuarioDTO.getNombre().isEmpty() && !usuarioDTO.getApellidos().isEmpty()
				&& !usuarioDTO.getEmail().isEmpty() && !usuarioDTO.getUsername().isEmpty();
		if (!seguir) {
			mav.addObject("usuarioDTO", usuarioDTO);
			mav.addObject("errorMessage", "Algunos valores son vacios");
			mav.setViewName("adminEditarUsuarios");
		} else {
			usuarioService.save(usuarioDTO);
			mav.setViewName("redirect:/adminClientes");
		}
		return mav;
	}

	@PostMapping("/usuarios/save")
	public ModelAndView save(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO) {

		log.info("UsuarioController - save:Salvamos los datos del usuarioDTO");
		log.info("UsuarioController - save:Salvamos los datos del usuarioDTO: " + usuarioDTO.toString());

		ModelAndView mav = new ModelAndView();
		boolean seguir = !usuarioDTO.getNombre().isEmpty() && !usuarioDTO.getApellidos().isEmpty()
				&& !usuarioDTO.getEmail().isEmpty() && !usuarioDTO.getUsername().isEmpty();
		if (!seguir) {
			mav.addObject("usuarioDTO", usuarioDTO);
			mav.addObject("errorMessage", "Algunos valores son vacios");
			mav.setViewName("cuenta");
		} else {
			usuarioService.save(usuarioDTO);
			mav.setViewName("redirect:/");
		}
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
	
	// ConfirmacionPagoParaEnviarCorreo
	@GetMapping("/usuarios/{usernameUsuario}/confirmacionCorreo")
	public ModelAndView enviarCorreoAlUser(@PathVariable("usernameUsuario") String usernameUsuario) {

		log.info("UsuarioController - adminEditarUsuarios: editamos la gestion de usuarios");
		ModelAndView mav = new ModelAndView("confirmacionPagoPremium");
		UsuarioDTO usuarioDTO = usuarioService.findByName(usernameUsuario);
		usuarioDTO = usuarioService.cambiarPremium(usuarioDTO);
		log.info(usuarioDTO.toString());

		mav.addObject("usuarioDTO", usuarioDTO);

		return mav;
	}
	
	// CancelacionPagoParaEnviarCorreo
		@GetMapping("/usuarios/{usernameUsuario}/cancelacionCorreo")
		public ModelAndView enviarCorreoAlUserDos(@PathVariable("usernameUsuario") String usernameUsuario) {

			log.info("UsuarioController - adminEditarUsuarios: editamos la gestion de usuarios");
			ModelAndView mav = new ModelAndView("cancelacionPagoPremium");
			UsuarioDTO usuarioDTO = usuarioService.findByName(usernameUsuario);
			usuarioDTO = usuarioService.cambiarEstandar(usuarioDTO);
			log.info(usuarioDTO.toString());

			mav.addObject("usuarioDTO", usuarioDTO);

			return mav;
		}

	// Alamcenar usuarios
	@PostMapping("/usuarios/saveNuevoUsuario")
	public ModelAndView saveNuevoUsuario(@ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO) {
		log.info("UsuarioController - save: Salvamos los datos del usuario:" + usuarioDTO.toString());
		// Invocamos a la capa de servicios para que almacene los datos del usuario
		usuarioService.saveNuevoUsuario(usuarioDTO);
		// Redireccionamos para volver a invocar a la raiz
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}
	
	// Premium
	@GetMapping("/zonaPremium/{usernameUsuario}")
	public ModelAndView zonaPremium(@PathVariable("usernameUsuario") String usernameUsuario) {

		ModelAndView mav = new ModelAndView("zonaPremium");
		UsuarioDTO usuarioDTO = usuarioService.findByName(usernameUsuario);

		mav.addObject("usuarioDTO", usuarioDTO);

		return mav;
	}
	
	// ConfirmacionConcurso
		@GetMapping("/usuarios/{usernameUsuario}/confirmacionConcurso")
		public ModelAndView confirmacionConcurso(@PathVariable("usernameUsuario") String usernameUsuario) {

			log.info("UsuarioController - adminEditarUsuarios: editamos la gestion de usuarios");
			ModelAndView mav = new ModelAndView("redirect:/zonaPremium/{usernameUsuario}");
			UsuarioDTO usuarioDTO = usuarioService.findByName(usernameUsuario);
			usuarioDTO = usuarioService.concursando(usuarioDTO);
			log.info(usuarioDTO.toString());

			mav.addObject("usuarioDTO", usuarioDTO);
		
			return mav;
		}
}
