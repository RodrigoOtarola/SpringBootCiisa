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

import com.example.demo.entity.Comuna;
import com.example.demo.entity.Proyecto;
import com.example.demo.service.ComunaService;
import com.example.demo.service.ProyectoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

	/** INYECCIÓN DE DEPENDENCIAS **/

	@Autowired
	private ProyectoService proyectoService;

	@Autowired
	private ComunaService comunaService;

	@GetMapping("/listar")
	public String proyectos(Model model) {

		List<Proyecto> proyecto = new ArrayList<Proyecto>();
		proyecto = proyectoService.findAll();

		model.addAttribute("titulo", "Proyectos");
		model.addAttribute("subtitulo", "Listar Proyectos");

		model.addAttribute("proyectos", proyecto);

		return "/fragments/proyectos/index";
	}

	@GetMapping("/crear")
	public String crearProyecto(Model model) {

		model.addAttribute("titulo", "Proyectos");
		model.addAttribute("subtitulo", "Crear Proyectos");

		List<Comuna> comunas = new ArrayList<Comuna>();
		comunas = comunaService.findAll();
		
		Proyecto proyecto = new Proyecto();

		// Pasamos los parametros a la vista
		model.addAttribute("comunas", comunas);
		model.addAttribute("proyecto", proyecto);

		return "fragments/proyectos/crear";
	}

	@PostMapping("/guardar")
	public String grabarProyecto(@Valid Proyecto proyecto, BindingResult resultado, Model model) {
		
		proyectoService.save(proyecto);

		return "redirect:/proyectos/listar";
	}
	
	/**VER Y EDITAR*/
	@RequestMapping(value = "/{opcion}/{id}")
	public String detalleProyecto(@PathVariable(value = "opcion") String opcion, @PathVariable(value = "id") Long id,
			Model model, RedirectAttributes flash) {
		
		Proyecto proyecto = new Proyecto();
		
		List<Comuna> comunas = new ArrayList<Comuna>();
		comunas = comunaService.findAll();
		
		//Proyecto proyecto = null;
		
		if (id > 0) {
			proyecto = proyectoService.findOne(id);
			if (proyecto == null) {
				flash.addFlashAttribute("error", "El ID de proyecto no existe en la BBDD!");
				return "redirect:/proyectos/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID de proyecto  no puede ser cero!");
			return "redirect:/proyectos/listar";
		}
		
		model.addAttribute("opcion", opcion);
		model.addAttribute("proyecto", proyecto);
		model.addAttribute("comunas", comunas);

		if (opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar Proyecto");
		} else {
			model.addAttribute("subtitulo", "Ver Proyecto");
		}		
		
		return "/fragments/proyectos/editar";		
	}
	
	/** ELIMINAR*/
	@RequestMapping(value = "eliminar/{id}")
	public String eliminarProyecto(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		if (id > 0) {
			proyectoService.delete(id);
		}

		return "redirect:/proyectos/listar";
	}

}
