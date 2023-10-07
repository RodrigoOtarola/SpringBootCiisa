package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modelos")
public class ModeloController {
	
	@GetMapping("/listar")
	public String modelo() {
		
		return "/fragments/modelos/index";
	}
	
	@GetMapping("/crear")
	public String crearModelo() {
		
		return "/fragments/modelos/crear";
	}

}
