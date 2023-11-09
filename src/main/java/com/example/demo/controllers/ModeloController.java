package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Proyecto;

@Controller
@RequestMapping("/modelos")
public class ModeloController {

	@GetMapping("/listar")
	public String modelo(Model model) {

		model.addAttribute("titulo", "Modelos");
		model.addAttribute("subtitulo", "Listar Modelos");

		return "/fragments/modelos/index";
	}

	@GetMapping("/crear")
	public String crearModelo(Model model) {

		model.addAttribute("titulo", "Modelos");
		model.addAttribute("subtitulo", "Crear Modelos");

		// Arreglo para mostrar proyectos

		List<Proyecto> proyecto = new ArrayList<>();
		
		proyecto.add(new Proyecto(1, "Parque Cerrillos", "Cerrillos"));
		proyecto.add(new Proyecto(2, "Parque Pajaritos ", "Maipú"));
		proyecto.add(new Proyecto(3, "Laguna del sol", "Padre Hurtado"));
		
		model.addAttribute("proyectos", proyecto);

		return "/fragments/modelos/crear";
	}

}
