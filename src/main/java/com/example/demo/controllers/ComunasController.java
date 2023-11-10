package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.IComunaDao;
import com.example.demo.entity.Comuna;
import com.example.demo.service.ComunaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/comunas")
public class ComunasController {

	@Autowired
	private ComunaService comunaService;

	@GetMapping("/listar")
	public String comunas(Model model) {

		model.addAttribute("titulo", "Comunas");
		model.addAttribute("subtitulo", "Listar Comunas");

		List<Comuna> comunas = new ArrayList<Comuna>();
		comunas = comunaService.findAll();
		model.addAttribute("comunas", comunas);

		return "/fragments/comunas/index";
	}

	@GetMapping("/crear")
	public String crearComunas(Model model) {

		model.addAttribute("titulo", "Comunas");
		model.addAttribute("subtitulo", "Crear Comunas");
		model.addAttribute("opcion", "crear");

		Comuna comuna = new Comuna();
		model.addAttribute("comuna", comuna);

		return "/fragments/comunas/crear";
	}

	@PostMapping("/guardar")
	public String guardarComuna(@Valid Comuna comuna, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			System.out.println("Error de validacion");
			model.addAttribute("titulo", "Comunas");
			model.addAttribute("subtitulo", "Crear Comunas");
			return "/fragments/comunas/crear";
		}

		comunaService.save(comuna);

		return "redirect:/comunas/listar";
	}

	@RequestMapping(value = "{opcion}/{id}")
	public String detalleComuna(
			// @PathVariable se pasan los parametros que utilizara la url
			@PathVariable(value = "opcion") String opcion, @PathVariable(value = "id") Long id,
			RedirectAttributes flash, Model model) {

		// Comuna comuna = null;

		Comuna comuna = new Comuna();

		if (id > 0) {

			comuna = comunaService.findOne(id);

			if (comuna == null) {
				flash.addFlashAttribute("error", "El ID de comuna no existe en la BBDD!");
				return "redirect:/comunas/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del la comuna no puede ser cero!");
			return "redirect:/comunas/listar";
		}

		model.addAttribute("opcion", opcion);
		model.addAttribute("comuna", comuna);

		if (opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar Comuna");
		} else {
			model.addAttribute("subtitulo", "Ver Comuna");
		}

		return "/fragments/comunas/editar";
	}

	/* EDITAR */
	@PutMapping("/update/{id}")
	public String actualizarComuna(@PathVariable Long id,
			@ModelAttribute("comunaActualizada") @Valid Comuna comunaActualizada, BindingResult resultado) {

		if (resultado.hasErrors()) {

			return "redirect:/comunas/editar/" + id;
		}

		comunaActualizada = comunaService.comunaUpdate(id, comunaActualizada);

		// comunaService.comunaUpdate(comunaActualizada);

		return "redirect:/comunas/listar";

	}

	/** ELIMINAR*/
	@RequestMapping(value = "eliminar/{id}")
	public String eliminarComuna(
			@PathVariable(value = "id") Long id,
			Model model, 
			RedirectAttributes flash) {
		
		
		if (id > 0) {	
			comunaService.delete(id);
		}
		
		return "redirect:/comunas/listar";
	}
}
