package com.example.demo.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Ejemplar;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {

//	@Query("SELECT e FROM Ejemplar e JOIN e.libro l WHERE e.prestado = false AND l.isbn = :isbn")
//	Optional<Ejemplar> findFirstByISBNDisponible(@Param("isbn") String isbn);
	
	
	@Query("SELECT e FROM Ejemplar e JOIN e.libro l WHERE e.prestado = false AND l.isbn = :isbn ORDER BY e.id ASC LIMIT 1")
	Optional<Ejemplar> findFirstByISBNDisponible(@Param("isbn") String isbn);

	@Modifying
	@Query("UPDATE Ejemplar e SET e.prestado = true WHERE e.id = :id")
	int prestarEjemplar(@Param("id") Long id);

	@Query(value = "SELECT m FROM Ejemplar m WHERE m.libro.isbn = :idA")
	public List<Ejemplar> findAllByLibro(@Param("idA") String libroIsbn);
	

}
