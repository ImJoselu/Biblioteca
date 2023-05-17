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

	@Query("SELECT u FROM Usuario u WHERE u.id IN (SELECT r.usuario.id FROM Rol r WHERE r.nombre = 'ROLE_USER')")
	List<Usuario> findAllClientes();

	@Modifying
	@Query ("UPDATE Usuario u SET u.nombre = :#{#usuario.nombre}, u.apellidos = :#{#usuario.apellidos}, u.email = :#{#usuario.email} , u.username = :#{#usuario.username} , u.es_administrador = :#{#usuario.es_administrador} , u.es_cliente = :#{#usuario.es_cliente} WHERE u.id = :#{#usuario.id}")
	void actualizarUsuario(@Param("usuario") Usuario usuario);

	@Query("SELECT u FROM Usuario u WHERE u.username = :username")
	Usuario findByUsername(@Param("username")String username);

	@Modifying
	@Query ("UPDATE Usuario u SET u.es_administrador = true WHERE u.id = :#{#usuario.id}")
	void cambiarPremium(@Param("usuario") Usuario usuario);

	@Modifying
	@Query ("UPDATE Usuario u SET u.es_administrador = false WHERE u.id = :#{#usuario.id}")
	void cambiarEstandar(@Param("usuario")Usuario usuario);

	@Modifying
	@Query ("UPDATE Usuario u SET u.es_cliente = true WHERE u.id = :#{#usuario.id}")
	void concursando(@Param("usuario")Usuario usuario);


}
