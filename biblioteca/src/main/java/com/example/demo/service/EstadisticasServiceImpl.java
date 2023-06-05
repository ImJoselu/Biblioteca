package com.example.demo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.EstadisticaDTO;
import com.example.demo.model.dto.LibroDTO;
import com.example.demo.repository.dao.AlquilerRepository;
import com.example.demo.repository.dao.EjemplarRepository;
import com.example.demo.repository.dao.GeneroRepository;
import com.example.demo.repository.dao.LibroRepository;
import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Genero;
import com.example.demo.repository.entity.Libro;
import com.example.demo.web.controller.IndexController;

@Service
public class EstadisticasServiceImpl implements EstadisticasService{

	private static final Logger log = LoggerFactory.getLogger(EstadisticasServiceImpl.class);
	
	@Autowired
	LibroRepository libroRepository;
	
	@Autowired
	GeneroRepository generoRepository;
	
	@Autowired
	AlquilerRepository alquilerRepository;

	@Override
	public EstadisticaDTO generosPopulares() {
		// TODO Auto-generated method stub
		EstadisticaDTO stat = new EstadisticaDTO();
		
		List<Genero> generos = generoRepository.findAll();
		
		
		for (Genero genero : generos) {
			stat.getLabels().add(genero.getNombre());
			
			int cantidad = alquilerRepository.numAlquileresPorGenero(genero.getNombre());
			stat.getData().add(cantidad);
		}
		
		return stat;
	}
	//HOLA
	//Para la pestaña tienda
	public List<LibroDTO> librosRecomendados(){
		List<Libro> libros = libroRepository.findAll();
		Map<Libro, Integer> mapa = new HashMap();
		
		for (Libro libro : libros) {
			List<Alquiler> alquileresTotal = libro.getListaEjemplares().stream() // obtener Stream<Ejemplar> de la lista de ejemplares
				    .flatMap(ejemplar -> ejemplar.getListaAlquileres().stream()) // obtener Stream<Alquiler> de la lista de alquileres de cada ejemplar
				    .filter(alquiler -> alquiler.getFecha_inicio().after(Date.from(LocalDate.now().minusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))) // filtrar los alquileres cuya fecha de inicio sea del último año
				    .collect(Collectors.toList()); // colectar los elementos en una lista
			
			mapa.put(libro, alquileresTotal.size());
		}
		
		for (Map.Entry<Libro, Integer> entry : mapa.entrySet()) {
		    System.out.println("Clave: " + entry.getKey().getTitulo() + ", Valor: " + entry.getValue());
		    
		}
		
		System.out.println("--------------------------------------------");
		
		Map<Libro, Integer> filteredMap = mapa.entrySet().stream()
		        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		        .limit(3)
		        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
		        
		for (Map.Entry<Libro, Integer> entry : filteredMap.entrySet()) {
		    System.out.println("Clave: " + entry.getKey().getTitulo() + ", Valor: " + entry.getValue());
		    
		}
		
		List<Libro> filteredBooks = filteredMap.keySet().stream().collect(Collectors.toList());
		List<LibroDTO> filteredBooksDTO = new ArrayList<>();
		
		for (int i = 0; i < filteredBooks.size(); i++) {
			LibroDTO libroDTO = LibroDTO.convertToDTO(filteredBooks.get(i));
			filteredBooksDTO.add(libroDTO);
		}
		
		return filteredBooksDTO;
		
		
	}
	
	//Para la pestaña Estadisticas
	//Por alguna rarisima razon, Este metodo y el de libros recomendados a igualdad de cantidades entre libros
	//cada uno elige un libro por encima del otro distinto y de manera consistente.
	@Override
	public EstadisticaDTO librosPopulares() {
		// TODO Auto-generated method stub
		EstadisticaDTO stat = new EstadisticaDTO();
		
		List<Libro> libros = libroRepository.findAll();
		
		Map<String, Integer> mapa = new HashMap();
		
		for (Libro libro : libros) {
			List<Alquiler> alquileresTotal = libro.getListaEjemplares().stream() // obtener Stream<Ejemplar> de la lista de ejemplares
				    .flatMap(ejemplar -> ejemplar.getListaAlquileres().stream()) // obtener Stream<Alquiler> de la lista de alquileres de cada ejemplar
				    .filter(alquiler -> alquiler.getFecha_inicio().after(Date.from(LocalDate.now().minusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))) // filtrar los alquileres cuya fecha de inicio sea del último año
				    .collect(Collectors.toList()); // colectar los elementos en una lista
			
			mapa.put(libro.getTitulo(), alquileresTotal.size());
		}
		
		//Puedo usar esto para los libros mas populares del inicio de la web
		
		Map<String, Integer> filteredMap = mapa.entrySet().stream()
		        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		        .limit(6)
		        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
		
		for (Map.Entry<String, Integer> entry : filteredMap.entrySet()) {
		    System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
		    
		}

		
		for (String clave : filteredMap.keySet()) {
		    Integer valor = filteredMap.get(clave);
		    
		    stat.getLabels().add(clave);
		    stat.getData().add(valor);
		}
		
		return stat;
	}

	@Override
	public EstadisticaDTO alquileresPorMes(int anyo) {
		// TODO Auto-generated method stub
		EstadisticaDTO stat = new EstadisticaDTO();
		
		LocalDate now = LocalDate.now();
		//int currentMonth = now.getMonthValue();
		
		String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		//String[] previousMonths = Arrays.copyOfRange(months, 0, currentMonth);
		
		HashMap<Integer, Integer> rs = alquilerRepository.findAlquileresPorMes(anyo);
		
		for (int i = 0; i < months.length; i++) {
			int key = i + 1;
			//Los meses del 1 al 12
			if(rs.containsKey(key)) {
				stat.getData().add(rs.get(key));
			}else {
				stat.getData().add(0);
			}
			
			stat.getLabels().add(months[i]);
		}
		
		return stat;
	}
}
