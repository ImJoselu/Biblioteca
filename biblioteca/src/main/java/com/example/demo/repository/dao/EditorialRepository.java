package com.example.demo.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Editorial;
import com.example.demo.repository.entity.Libro;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface EditorialRepository extends JpaRepository<Editorial, Long> {

	
	@Query("SELECT l FROM Editorial l WHERE l.nombre = :nombre")
	Editorial findByNombre(@Param("nombre") String nombre);
}
