
package com.example.demo.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.LibroDTO;
import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.AlquilerDTO;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.dao.AlquilerRepository;
import com.example.demo.repository.dao.EjemplarRepository;
import com.example.demo.repository.dao.LibroRepository;
import com.example.demo.repository.dao.UsuarioRepository;
import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Ejemplar;
import com.example.demo.repository.entity.Usuario;
import com.example.demo.repository.entity.Alquiler;

@Service
public class AlquilerServiceImpl implements AlquilerService {

	private static final Logger log = LoggerFactory.getLogger(AlquilerServiceImpl.class);

	@Autowired
	private AlquilerRepository alquilerRepository;
	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EjemplarRepository ejemplarRepository;

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

	@Override
	public EjemplarDTO alquilar(UsuarioDTO usuarioDTO, LibroDTO libroDTO) {
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioDTO.getId());
		Optional<Ejemplar> optionalEjemplar = ejemplarRepository.findFirstByISBNDisponible(libroDTO.getIsbn());

		// Obtener el Optional<Ejemplar> de algún lugar
		if (optionalEjemplar.isPresent()) {
			Ejemplar ejemplar = optionalEjemplar.get();
			// Resto del código que utiliza el objeto Ejemplar

			Alquiler alquiler = new Alquiler();
			alquiler.setEjemplar(ejemplar);
			alquiler.setUsuario(usuario.get());
			alquiler.setFecha_entrega(null);
			Timestamp ts = Timestamp.from(Instant.now());
			alquiler.setFecha_inicio(ts);

			Timestamp ts2 = Timestamp.from(Instant.now());
			Calendar cal = Calendar.getInstance();
			cal.setTime(ts2);
			cal.add(Calendar.DATE, 14);
			ts2.setTime(cal.getTime().getTime());
			alquiler.setFecha_limite(ts2);

			ejemplarRepository.prestarEjemplar(ejemplar.getId());

			alquilerRepository.save(alquiler);

			return EjemplarDTO.convertToDTO(ejemplar);

		} else {
			EjemplarDTO ejemplarDTO = new EjemplarDTO();
			ejemplarDTO.setId(0L);
			return ejemplarDTO;

		}

	}

}