package com.example.demo.repository.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "libro_escribe_autor")
public class LibroEscribeAutor {
	
	private Long id;
	@ManyToOne
	@JoinColumn(name = "fk_libro_escribe")
	private Libro libro;
	@ManyToOne
	@JoinColumn(name = "fk_autor_escribe")
	private Autor autor;
	@Column(name = "fecha_publicacion")
	private Date fechaPublicacion;
	@Column(name = "edad_recomendada")
	private Date edadRecomendada;
	
	
	
	
}
