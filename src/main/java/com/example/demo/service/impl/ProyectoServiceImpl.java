package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IProyectoDao;
import com.example.demo.entity.Proyecto;
import com.example.demo.service.ProyectoService;

import jakarta.transaction.Transactional;

@Service
public class ProyectoServiceImpl implements ProyectoService{
	
	@Autowired
	private IProyectoDao iProyectoDao;
	
	@Override
	public List<Proyecto> findAll(){
		return (List<Proyecto>) iProyectoDao.findAll();
	}
	
	@Override
	public void save(Proyecto proyecto) {
		iProyectoDao.save(proyecto);
	}
	
	@Override
	public Proyecto findOne(Long id) {
		return iProyectoDao.findById(id).orElse(null);
	}
	
	@Transactional
	public void delete(Long id) {
		iProyectoDao.deleteById(id);
	}
	

}
