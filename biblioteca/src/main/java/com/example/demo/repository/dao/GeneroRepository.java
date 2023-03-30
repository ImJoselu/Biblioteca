package com.example.demo.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.entity.Ejemplar;
import com.example.demo.repository.entity.Genero;

import jakarta.transaction.Transactional;

@Transactional
public interface GeneroRepository extends JpaRepository<Genero, Long>{

}
