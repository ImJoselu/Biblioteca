package com.example.demo.repository.dao;

import com.example.demo.repository.entity.Solicitud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

	@Query(value = "SELECT s FROM Solicitud s WHERE s.usuario.id = :idUsu")
	public List<Solicitud> findAllByUsuario(@Param("idUsu") Long idUsuario);

	@Modifying
	@Query("UPDATE Solicitud s SET s.estado = :estado WHERE s.id = :id")
	public int updateEstadoById(@Param("id") Long id, @Param("estado") String estado);

}
