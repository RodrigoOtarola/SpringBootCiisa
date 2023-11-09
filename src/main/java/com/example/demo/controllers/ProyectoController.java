package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Comuna;
import com.example.demo.service.ComunaService;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {
	
	
	/**INYECCIÓN DE DEPENDENCIAS**/
	
	@Autowired
	private ComunaService comunaService;
	
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
		
		List<Comuna> comunas = new ArrayList<Comuna>();
		comunas = comunaService.findAll();
		
		//Pasamos los parametros a la vista
		model.addAttribute("comunas", comunas);
		
		return "/fragments/proyectos/crear";
	}

}
