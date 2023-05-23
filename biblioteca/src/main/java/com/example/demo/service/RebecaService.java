package com.example.demo.service;

import com.example.demo.repository.entity.LibroRebeca;

public interface RebecaService {

	LibroRebeca performSearch(String isbn13);

	boolean save(LibroRebeca libroRebeca);

}
