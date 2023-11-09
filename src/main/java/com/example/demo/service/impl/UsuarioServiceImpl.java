package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUsuarioDao;
import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private IUsuarioDao iUsuarioDao;
	
	@Override
	public List<Usuario> findAll(){
		return (List<Usuario>) iUsuarioDao.findAll();
	}
	
	@Override
	public void save(Usuario usuario) {
		iUsuarioDao.save(usuario);
	}
	
	@Override
	public Usuario findOne(Long id) {
		return iUsuarioDao.findById(id).orElse(null);
	}
	
	@Transactional
	public void delete(Long id) {
		iUsuarioDao.deleteById(id);
	}
	

}
