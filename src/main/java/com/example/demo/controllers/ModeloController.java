package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModeloController {
	
	@GetMapping("/modelos/listar")
	public String modelo() {
		
		return "/fragments/modelos/index";
	}
	
	@GetMapping("/modelos/crear")
	public String crearModelo() {
		
		return "/fragments/modelos/crear";
	}

}
