package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CotizacionController {
	
	/*Método que muestra vista de cotizaciones creadas, archivadas o eliminadas. */	
	@GetMapping("/cotizaciones/listar")
	public String cotizacion() {
		
		return "/fragments/cotizaciones/index";
	}
	
	/*Método que crea cotización*/
	@GetMapping("/cotizaciones/crear")
	public String crear() {
		
		return "/fragments/cotizaciones/crear";
	}
	

}
