package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.service.RolService;
import com.example.demo.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	private RolService rolService;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/listar")
	public String listarUsuarios(Model model) {

		model.addAttribute("titulo", "Usuarios");
		model.addAttribute("subtitulo", "Listar Usuarios");

		List<Usuario> usuario = new ArrayList<Usuario>();
		usuario = usuarioService.findAll();

		model.addAttribute("usuarios", usuario);

		return "/fragments/usuarios/index";
	}

	@GetMapping("/crear")
	public String crearUsuario(Model model) {

		// Rol rol = new Rol();
		List<Rol> rol = new ArrayList<Rol>();
		rol = rolService.findAll();

		model.addAttribute("roles", rol);

		Usuario usuario = new Usuario();

		// Atributos que pasamos a la vista
		model.addAttribute("titulo", "Usuarios");
		model.addAttribute("subtitulo", "Crear Usuarios");

		model.addAttribute("usuario", usuario);

		// enviamos los datos, para poblar Checkbox
		// model.addAttribute("roles", rol);

		return "/fragments/usuarios/crear";
	}

	/** INSERTAR USUARIO */

	@PostMapping("/guardar")
	public String grabarUsuario(@Valid Usuario usuario, BindingResult resultado, Model model) {

		if (resultado.hasErrors()) {

			model.addAttribute("titulo", "Usuarios");
			model.addAttribute("subtitulo", "Crear Usuarios");
			return "/fragments/usuarios/crear";
		}

		// Grabar en DDBB
		usuarioService.save(usuario);

		return "redirect:/usuarios/listar";
	}

	/** VER Y EDITAR */

	@RequestMapping(value = "/{opcion}/{id}")
	public String detalleUsuario(@PathVariable(value = "opcion") String opcion, @PathVariable(value = "id") Long id,
			RedirectAttributes flash, Model model) {

		Usuario usuario = new Usuario();
		
		List<Rol> rol = new ArrayList<Rol>();
		rol = rolService.findAll();


		// Validaciones
		if (id > 0) {
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El ID de usuario no existe en la DDBB");
				return "redirect:/usuarios/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID de usuario no puede ser cero");
			return "redirect:/usuarios/listar";
		}

		model.addAttribute("opcion", opcion);
		model.addAttribute("usuario", usuario);
		model.addAttribute("roles", rol);

		if (opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar Usuario");
		} else {
			model.addAttribute("subtitulo", "Ver Usuario");
		}

		return "/fragments/usuarios/editar";
	}
	
	/** ELIMINAR*/
	@RequestMapping(value = "eliminar/{id}")
	public String eliminarUsuario(
			@PathVariable(value = "id") Long id,
			Model model, 
			RedirectAttributes flash) {
		
		
		if (id > 0) {	
			usuarioService.delete(id);
		}
		
		return "redirect:/usuarios/listar";
	}
}
