package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Proyecto;

public interface IProyectoDao extends CrudRepository<Proyecto, Long> {

}
