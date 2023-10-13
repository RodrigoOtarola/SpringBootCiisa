package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Comuna;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {
	
	@GetMapping("/listar")
	public String proyectos(Model model) {
		
		model.addAttribute("titulo", "Proyectos");
		model.addAttribute("subtitulo", "Listar Proyectos");
		
		return "/fragments/proyectos/index";
	}
	
	@GetMapping("/crear")
	public String crearProyecto(Model model) {
		
		model.addAttribute("titulo", "Proyectos");
		model.addAttribute("subtitulo", "Crear Proyectos");
		
		List<Comuna> comuna = new ArrayList<>();
		comuna.add(new Comuna(1, "Santiago", 0));
		comuna.add(new Comuna(2, "Cerrillos", 0));
		comuna.add(new Comuna(3, "Maipu", 0));
		comuna.add(new Comuna(4, "Providencia", 0));
		
		//Pasamos los parametros a la vista
		model.addAttribute("comunas", comuna);
		
		return "/fragments/proyectos/crear";
	}

}
