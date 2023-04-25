package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Usuario;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "SELECT a FROM Usuario a WHERE a.es_cliente = true")
	List<Usuario> findAllClientes();

	 @Modifying
	 @Query ("UPDATE Usuario u SET u.nombre = :#{#usuario.nombre}, u.apellidos = :#{#usuario.apellidos}, u.email = :#{#usuario.email} , u.username = :#{#usuario.username} WHERE u.id = :#{#usuario.id}")
	  void actualizarUsuario(@Param("usuario") Usuario usuario);
}
