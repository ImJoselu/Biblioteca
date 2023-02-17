package com.example.demo.repository.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.repository.entity.Solicitud;

import jakarta.transaction.Transactional;


@Repository
@Transactional
public interface SolicitudRepository extends JpaRepository<Solicitud, Long>{

	@Query(value = "SELECT s FROM Solicitud s WHERE s.usuario.id = :idUsu")
	public List<Solicitud> findAllByUsuario(@Param("idUsu") Long idUsuario);

}
