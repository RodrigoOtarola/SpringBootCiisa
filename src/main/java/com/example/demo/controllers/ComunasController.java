package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comunas")
public class ComunasController {
	
	@GetMapping("/listar")
	public String comunas(Model model) {
		
		model.addAttribute("titulo", "Comunas");
		model.addAttribute("subtitulo", "Listar Comunas");
		
		return "/fragments/comunas/index";
	}
	
	@GetMapping("/crear")
	public String crearComunas(Model model) {
		
		model.addAttribute("titulo", "Comunas");
		model.addAttribute("subtitulo", "Crear Comunas");
		
		return "/fragments/comunas/crear";
	}
}
