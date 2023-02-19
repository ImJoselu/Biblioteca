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

}