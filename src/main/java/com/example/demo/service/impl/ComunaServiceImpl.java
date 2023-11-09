package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IComunaDao;
import com.example.demo.entity.Comuna;
import com.example.demo.service.ComunaService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ComunaServiceImpl implements ComunaService {
	
	@Autowired
	private IComunaDao iComunaDao;
	
	@Override
	public List<Comuna> findAll(){
		return (List<Comuna>) iComunaDao.findAll();
		
	} 
	
	@Override
	public void save(Comuna comuna) {
		iComunaDao.save(comuna);
		
	}
	
	@Override
	public Comuna findOne(Long id) {
		return iComunaDao.findById(id).orElse(null);
	}
	
	@Transactional
	public Comuna comunaUpdate(Long id, Comuna comunaActualizada) {
		
		/*Comuna comuna = iComunaDao.findById(id).orElse(null);
		
		comuna.setNombre(comunaActualizada.getNombre());
		
		return iComunaDao.save(comuna);*/
		
		Comuna comuna = iComunaDao.findById(id).orElse(null);

	    if (comuna != null) {
	        comuna.setNombre(comunaActualizada.getNombre());
	        return iComunaDao.save(comuna);
	    } else {
	        // Manejar la lógica para el caso en que no se encuentre la comuna
	        return null;
	    }
		
	}
	
	/*@Override
	public void delete(Long id) {
		iComunaDao.(null);
	}*/
	
	
	@Transactional 
	public void delete(Long id) {
		iComunaDao.deleteById(id);
	}
	

}
