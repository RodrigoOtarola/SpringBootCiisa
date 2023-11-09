package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Rol;

//Implementaciones desde el rol service
public interface IRolDao extends CrudRepository<Rol, Long>{

}
