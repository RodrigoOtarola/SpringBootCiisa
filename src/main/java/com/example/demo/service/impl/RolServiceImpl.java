package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IRolDao;
import com.example.demo.entity.Rol;
import com.example.demo.service.RolService;

@Service
public class RolServiceImpl implements RolService{

	@Autowired
	private IRolDao iRolDao;
	
	@Override
	public List<Rol> findAll(){
		return (List<Rol>) iRolDao.findAll();
	}
	
	@Override
	public Rol findOne(Long id) {
		return iRolDao.findById(id).orElse(null);
	}
	
}
