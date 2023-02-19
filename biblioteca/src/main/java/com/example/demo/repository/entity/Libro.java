package com.example.demo.repository.entity;

/* FALTA CONSTRUCTOR N - N  de genero y autor*/

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "libro")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String isbn;

	private String titulo;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "libro")
	private Set<Ejemplar> listaEjemplares;

	@ManyToOne
	@JoinColumn(name = "fk_editorial")
	private Editorial editorial;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// Tabla que mantiene la relacion N-N
	@JoinTable(
			// Nombre de la tabla
			name = "libro_pertenece_genero",
			// columna que almacena el id de cliente en la tabla libro_pertenece_genero
			joinColumns = @JoinColumn(name = "fk_libro_pertenece"),
			// columna que almacena el id de la direccion en la tabla libro_pertenece_genero
			inverseJoinColumns = @JoinColumn(name = "fk_genero_pertenece"))
	@ToString.Exclude
	private Set<Genero> listaGeneros;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "libro")
	private Set<LibroEscribeAutor> listaLibroEscribeAutor;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(isbn, other.isbn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	public Libro() {
		this.listaEjemplares = new HashSet<Ejemplar>();
		this.editorial = new Editorial();
	}

}
