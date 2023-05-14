package com.example.demo.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Libro;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface LibroRepository extends JpaRepository<Libro, Long> {

	@Query("SELECT l FROM Libro l ORDER BY l.id DESC LIMIT 6")
	List<Libro> findTop6ByOrderByIdDesc();

	@Query("SELECT l FROM Libro l WHERE l.isbn = :isbnLibro")
	Libro findByIsbn(@Param("isbnLibro") String isbnLibro);

	@Query("SELECT l FROM Libro l where isbn = :isbn")
	Libro findByISBN(String isbn);

}
