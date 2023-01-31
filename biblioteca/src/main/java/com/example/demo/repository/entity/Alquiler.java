package com.example.demo.repository.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "alquiler")
public class Alquiler {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date fecha_inicio;

	private Date fecha_limite;

	private Date fecha_entrega;
	
	@ManyToOne
	@JoinColumn(name = "fk_usuario_alquiler")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "fk_ejemplar")
	private Ejemplar ejemplar;
	

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "alquiler")
	@ToString.Exclude
	private Set<Multa> listaMultas;


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	public Alquiler() {
		this.usuario = new Usuario();
		this.ejemplar = new Ejemplar();
		this.listaMultas = new HashSet<Multa>();
	}

	
}
