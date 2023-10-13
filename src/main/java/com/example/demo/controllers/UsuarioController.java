package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Rol;

@Controller
@RequestMapping("usuarios")
public class UsuarioController {
	
	
	@GetMapping("/listar")
	public String listarUsuarios(Model model) {
		
		model.addAttribute("titulo", "Usuarios");
		model.addAttribute("subtitulo", "Listar Usuarios");
		
		return "/fragments/usuarios/index";
	}
	
	@GetMapping("/crear")
	public String crearUsuario(Model model) {
		
		model.addAttribute("titulo", "Usuarios");
		model.addAttribute("subtitulo", "Crear Usuarios");
		
		//Rol rol = new Rol();
		List<Rol> rol = new ArrayList<>();
		
		rol.add(new Rol( 1,"Usuario"));
		rol.add(new Rol(2,"Ejecutivo"));
		rol.add(new Rol(3,"Administrador"));
		
		//enviamos los datos, para poblar Checkbox
		model.addAttribute("roles", rol);
		
		return "/fragments/usuarios/crear";
	}

}
