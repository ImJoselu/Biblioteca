package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Libro;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface LibroRepository extends JpaRepository<Libro, Long> {

	@Query("SELECT l FROM Libro l ORDER BY l.id DESC LIMIT 6")
	List<Libro> findTop6ByOrderByIdDesc();

}
