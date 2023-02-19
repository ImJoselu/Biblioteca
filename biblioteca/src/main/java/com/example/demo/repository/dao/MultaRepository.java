package com.example.demo.repository.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.dto.MultaDTO;
import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Multa;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface MultaRepository extends JpaRepository<Multa, Long> {

	@Query(value = "SELECT m FROM Multa m WHERE m.alquiler.id = :idA")
	public List<Multa> findAllByAlquiler(@Param("idA") Long idAlquiler);

	/*
	 * select multa.* from usuario join alquiler on usuario.id =
	 * alquiler.fk_usuario_alquiler join multa on alquiler.id = multa.fk_alquiler
	 * where usuario.id = 1;
	 */
	@Query("SELECT m from Usuario u JOIN u.listaAlquileres a JOIN a.listaMultas m WHERE u.id = :idU")
	public List<Multa> findAllByCliente(@Param("idU") Long idCliente);

}
