package com.example.demo.repository.entity;

import java.sql.Date;

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
@Table(name = "multa")
public class Multa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date fecha;

	private Boolean descartada;

	private Double importe;

	private String observaciones;
	
	@ManyToOne
	@JoinColumn(name = "fk_alquiler")
	private Alquiler alquiler;
}
