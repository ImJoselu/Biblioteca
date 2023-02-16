package com.example.demo.repository.dao;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.repository.entity.Solicitud;

import jakarta.transaction.Transactional;





@Repository
@Transactional
public interface SolicitudRepository extends JpaRepository<Solicitud, Long>{

}
