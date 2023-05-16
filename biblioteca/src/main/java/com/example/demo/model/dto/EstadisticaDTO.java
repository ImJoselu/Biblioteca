package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EstadisticaDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> labels;
	private List<Integer> data;
	public EstadisticaDTO() {
		super();
		this.labels = new ArrayList();
		this.data = new ArrayList();
	}
	
	

}
