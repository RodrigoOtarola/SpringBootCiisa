package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RolController {

	public ModelAndView selectRol(ModelAndView rol) {
		
		rol.addObject("rol","prueba");
		
		rol.setViewName("/fragments/usuarios/__form");
		
		return rol;
	}
	
}
