package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {
	
	@GetMapping("/listar")
	public String proyectos() {
		
		return "/fragments/proyectos/index";
	}
	
	@GetMapping("/crear")
	public String crearProyecto() {
		
		return "/fragments/proyectos/crear";
	}

}
