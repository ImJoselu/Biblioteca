package com.example.demo.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Comment;
import com.example.demo.repository.entity.Post;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query(value = "SELECT a FROM Comment a WHERE a.usuario.id = :idU")
	public List<Comment> findAllByUsuario(@Param("idU") Long idUsuario);

	@Query(value = "SELECT a FROM Comment a WHERE a.post.id = :idP")
	public Comment[] findAllByPost(@Param("idP") Long id);

}