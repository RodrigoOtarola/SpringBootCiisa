package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProyectoController {
	
	@GetMapping("/proyectos/listar")
	public String proyectos() {
		
		return "/fragments/proyectos/index";
	}
	
	@GetMapping("/proyectos/crear")
	public String crearProyecto() {
		
		return "/fragments/proyectos/crear";
	}

}
