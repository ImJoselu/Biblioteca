package com.example.demo.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Autor;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AutorRepository extends JpaRepository<Autor, Long> {

}
