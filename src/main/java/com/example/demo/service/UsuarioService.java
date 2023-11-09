package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Usuario;

public interface UsuarioService {

	//Listamos usuarios
	public List<Usuario> findAll();
	
	//Insertar usuario
	public void save(Usuario usuario);
	
	//Buscamos por id
	public Usuario findOne(Long id);
	
	//Eliminar usuario
	public void delete(Long id);
	
}
