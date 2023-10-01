package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComunasController {
	
	@GetMapping("/comunas/listar")
	public String comunas() {
		
		return "/fragments/comunas/index";
	}
	
	@GetMapping("/comunas/crear")
	public String crearComunas() {
		
		return "/fragments/comunas/crear";
	}
}
