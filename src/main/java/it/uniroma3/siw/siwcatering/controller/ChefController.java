package it.uniroma3.siw.siwcatering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siwcatering.model.Chef;
import it.uniroma3.siw.siwcatering.service.ChefService;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;

	@GetMapping("/admin/inserisci-chef")
	public String getInserisciChefForm(Model model) {
		
		model.addAttribute("chef", new Chef());
		
		return "inserisci-chef";
		
	}
	
	@PostMapping("/admin/chef")
	public String createChef(@Valid @ModelAttribute("chef") Chef chef,
			BindingResult chefBindingResult) {
		
		if (!chefBindingResult.hasErrors()) {
			chefService.save(chef);
			return "redirect:/admin";
		}
		
		return "inserisci-chef";
	}
	
}
