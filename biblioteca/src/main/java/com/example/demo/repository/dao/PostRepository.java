package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Post;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

	@Query(value = "SELECT a FROM Post a WHERE a.usuario.id = :idU")
	public List<Post> findAllByUsuario(@Param("idU") Long idUsuario);

}