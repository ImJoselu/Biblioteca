package com.example.demo.repository.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.dto.MultaDTO;
import com.example.demo.repository.entity.Alquiler;
import com.example.demo.repository.entity.Multa;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface MultaRepository extends JpaRepository<Multa, Long>{

	

}
