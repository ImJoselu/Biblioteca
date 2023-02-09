package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.MultaDTO;

@Service
public interface MultaService {

	List<MultaDTO> findAll();

}
