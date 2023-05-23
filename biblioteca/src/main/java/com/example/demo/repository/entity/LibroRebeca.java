package com.example.demo.repository.entity;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.EditorialDTO;
import com.example.demo.model.dto.EjemplarDTO;
import com.example.demo.model.dto.LibroDTO;

import lombok.Data;

@Data
public class LibroRebeca {

	private String copyright; 		//017 $a		Copyright or Legal Deposit Number
	private String isbn13; 			//020 $a o $z	isbn13
	private String catSource; 		//040 $a		Cataloging Source
	private String udsn; 			//080 $a		Universal Decimal Classification Number
	private String autor; 			//100 $ad		Autor + Año nacimiento
	private String titulo; 			//245 $ac		Titulo + Autora(A veces traduccion) o edicion
	private String edicion;			//250 $a 		Edicion
	private String publicacion; 	//260 $abc		Ubicacion + Editorial + año publicacion
	private String datosFisicos; 	//300 $ac   	Paginas + tamaño
	private String notas;			//500 $a		Notas Pertinentes
	private String genero;			//655 $a		genero
	
	private MultipartFile imagen;
	
	
	//Muy basico, podria añadir editorial, genero, autor...
	public static Libro convertToLibro(LibroRebeca libroRebeca) {
		
		Libro libro = new Libro();
		libro.setIsbn(libroRebeca.getIsbn13());
		libro.setTitulo(libroRebeca.getTitulo());
		
		Ejemplar ejemplar = new Ejemplar();
		ejemplar.setLocalizacion(libroRebeca.getUdsn());
		ejemplar.setPrestado(false);
		ejemplar.setLibro(libro);
		
		Editorial editorial = new Editorial();
		editorial.setNombre(libroRebeca.getPublicacion());
		editorial.getListaLibros().add(libro);
		
		
		libro.setEditorial(editorial);
		libro.getListaEjemplares().add(ejemplar);

		return libro;
	}
}




/*
001$$ES-MaREB00875036-3
003$$ES-MaREB
005$$20190907:02013200
008$$071001s2007    esp    j      ||| ||spa d
017$  $a[a=NA 1358-2007]
020$  $a[a=978-84-7888-445-2]
040$  $a[a=BR-MU]
080$  $a[a=82-36]
100$1 $ad[a=Rowling, J. K. (, d=1965-)]
245$10$ac[a=Harry Potter y la piedra filosofal / , c=J. K. Rowling.]
250$  $a[a=47� ed.]
260$  $abc[a=Barcelona : , b=Salamandra, , c=2007.]
300$  $ac[a=254 p. ; , c=22 cm.]
490$0 $av[a=Harry Potter ; , v=1]
*/
