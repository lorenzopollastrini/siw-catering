package it.uniroma3.siw.siwcatering.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siwcatering.model.Chef;
import it.uniroma3.siw.siwcatering.service.ChefService;

@Controller
public class MainController {
	
	@Autowired
	private ChefService chefService;

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/admin")
	public String adminHome(Model model) {
		
		Collection<Chef> chefs = chefService.findAll();
		
		model.addAttribute("chefs", chefs);
		
		return "admin-home";
	}
	
}
