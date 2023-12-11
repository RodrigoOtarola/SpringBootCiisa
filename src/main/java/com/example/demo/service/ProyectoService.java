package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Proyecto;

public interface ProyectoService {
	
	//Listamos Proyectos
	public List<Proyecto> findAll();
	
	//Insertar usuario
	public void save(Proyecto proyecto);
	
	//Buscamos por id
	public Proyecto findOne(Long id);
	
	//Eliminar usuario
	public void delete(Long id);

}
