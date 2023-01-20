package com.example.demo.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "solicitud")
public class Solicitud {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String email;
	private String ISBN;
	private String titulo;
	private String mensaje;
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "fk_usuario_solicitud")
	private Usuario usuario;

}
