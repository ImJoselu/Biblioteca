package com.example.demo.repository.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Ejemplar;
import com.example.demo.repository.entity.Multa;
import com.example.demo.repository.entity.Usuario;

import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.Data;

@Repository
@Transactional
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

	@Query(value = "SELECT a FROM Alquiler a WHERE a.usuario.id = :idU")
	public List<Alquiler> findAllByUsuario(@Param("idU") Long idUsuario);

	
    @Query(value = "SELECT COUNT(*) AS cantidad_alquileres " +
            "FROM alquiler " +
            "JOIN ejemplar ON alquiler.fk_ejemplar = ejemplar.id " +
            "JOIN libro ON ejemplar.fk_libro = libro.isbn " +
            "JOIN libro_pertenece_genero ON libro.isbn = libro_pertenece_genero.fk_libro_pertenece " +
            "JOIN genero ON libro_pertenece_genero.fk_genero_pertenece = genero.id " +
            "WHERE genero.nombre = :nombreGenero", nativeQuery = true)
	public int numAlquileresPorGenero(@Param("nombreGenero") String nombreGenero);
    
   @Query(value = "SELECT MONTH(fecha_inicio) AS mes, COUNT(*) AS total "
            + "FROM alquiler "
            + "WHERE YEAR(fecha_inicio) = :anyo "
            + "GROUP BY MONTH(fecha_inicio)", nativeQuery = true)
    public List<Tuple> numeroAlquileresPorMes(@Param("anyo") int anyo);
   
   default HashMap<Integer, Integer> findAlquileresPorMes(int anyo) {
	    List<Tuple> result = numeroAlquileresPorMes(anyo);
	    HashMap<Integer, Integer> alquileresPorMes = new HashMap<>();

	    for (Tuple tuple : result) {
	        int mes = tuple.get("mes", Integer.class);
	        int total = tuple.get("total", Long.class).intValue();

	        alquileresPorMes.put(mes, total);
	    }

	    return alquileresPorMes;
   }

}