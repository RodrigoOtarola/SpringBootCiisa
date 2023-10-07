package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cotizaciones")
public class CotizacionController {
	
	/*Método que muestra vista de cotizaciones creadas, archivadas o eliminadas. */	
	@GetMapping("/listar")
	public String cotizacion() {
		
		return "/fragments/cotizaciones/index";
	}
	
	/*Método que crea cotización*/
	@GetMapping("/crear")
	public String crear() {
		
		return "/fragments/cotizaciones/crear";
	}
	

}
