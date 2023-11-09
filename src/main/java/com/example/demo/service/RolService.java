package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Rol;

public interface RolService {
	
	/** Metodos implementados en RolService **/
	
	//Lista de Roles
	public List<Rol> findAll();
	
	//Rol por ID
	public Rol findOne(Long id);
}
