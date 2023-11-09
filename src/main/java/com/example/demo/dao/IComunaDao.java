package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Comuna;

//Pedira Dependencia de la Entity y el ID (tipo de dato), implementamos todo desde el service
public interface IComunaDao extends CrudRepository<Comuna, Long> {

}
