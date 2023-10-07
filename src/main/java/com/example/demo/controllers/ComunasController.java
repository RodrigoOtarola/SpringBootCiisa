package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comunas")
public class ComunasController {
	
	@GetMapping("/listar")
	public String comunas() {
		
		return "/fragments/comunas/index";
	}
	
	@GetMapping("/crear")
	public String crearComunas() {
		
		return "/fragments/comunas/crear";
	}
}
