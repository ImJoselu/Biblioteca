package com.example.demo.repository.dao;

import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.entity.Solicitud;

@Repository
@Transactional
public interface SolicitudRepository extends JpaRepository<Solicitud, Long>{

}
