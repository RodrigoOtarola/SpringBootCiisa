package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Comuna;
import com.example.demo.entity.Proyecto;
import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.service.ComunaService;
import com.example.demo.service.ProyectoService;
import com.example.demo.service.RolService;
import com.example.demo.service.UsuarioService;

@Controller
public class DashboardController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ComunaService comunaService;
	
	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private RolService rolService;

	/*Retorna la vista unica del dashboard*/
	@GetMapping({"/","/index","/home"})
	public String dashboard(Model model) {
		
		
		List<Usuario> usuarios = usuarioService.findAll();
		model.addAttribute("usuarios",usuarios);
		
		List<Comuna> comunas = comunaService.findAll();
		model.addAttribute("comunas",comunas);
		
		List<Proyecto> proyectos = proyectoService.findAll();
		model.addAttribute("proyectos",proyectos);
		
		List<Rol> roles= rolService.findAll();
		model.addAttribute("roles",roles);
		
		
		
		model.addAttribute("titulo","Dashboard");
		
		return "fragments/dashboard/index";
	}
}
