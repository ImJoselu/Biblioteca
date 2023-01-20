package com.example.demo.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MultaDTO {
	
	private Long id;
	private Date fecha;
	private Boolean descartada;
	private Double importe;
	private String observaciones;
}
