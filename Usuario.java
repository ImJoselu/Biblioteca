package com.example.demo.repository.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String nif;
		private String nombre;
		private String apellidos;
		private String email;
		
		private String username;
		private String password;
		
		private boolean es_administrador;
		private boolean es_cliente;

		@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
		@ToString.Exclude
		private Set<Solicitud> solicitud;

		@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
		@ToString.Exclude
		private Set<Alquiler> alquiler;
	
	
}
