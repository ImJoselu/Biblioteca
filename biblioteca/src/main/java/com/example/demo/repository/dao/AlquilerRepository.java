package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Alquiler;

import jakarta.transaction.Transactional;

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

	
}