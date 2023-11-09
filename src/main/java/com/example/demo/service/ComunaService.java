package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Comuna;

public interface ComunaService {

	/** METODOS IMPLEMENTADOS DESDE EL SERVICE**/
	
	//Lista comunas
	public List<Comuna> findAll();
	
	//Insertamos comuna, recibimos arreglo de comuna
	public void save(Comuna comuna);
	
	//Buscamos comuna por ID
	public Comuna findOne(Long id);
	
	//Update
	public Comuna comunaUpdate(Long id,Comuna comunaActualizada);
	
	//Eliminamos comuna con id, solo cambio de estado.
	public void delete(Long id);
}
