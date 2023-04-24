package com.example.demo.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Genero;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface GeneroRepository extends JpaRepository<Genero, Long> {

}
