package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	/*Retorna la vista unica del dashboard*/
	@GetMapping("/")
	public String dashboard(Model model) {
		
		model.addAttribute("titulo","Dashboard");
		
		return "fragments/dashboard/index";
	}
}
