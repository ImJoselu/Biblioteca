package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.AlquilerDTO;

@Service
public interface AlquilerService {

	List<AlquilerDTO> findAll();

}
