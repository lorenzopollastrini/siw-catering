package it.uniroma3.siw.siwcatering.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siwcatering.model.Chef;
import it.uniroma3.siw.siwcatering.service.ChefService;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;

	@GetMapping("/chef")
	public String getElencoChef(Model model) {
		
		Collection<Chef> chefs = chefService.findAll();
		
		model.addAttribute("chefs", chefs);
		
		return "elenco-chef";
		
	}
	
	@GetMapping("/chef/{chefId}")
	public String getChef(@PathVariable("chefId") Long chefId,
			Model model) {
		
		Chef chef = chefService.findById(chefId);
		
		model.addAttribute("chef", chef);
		
		return "dettagli-chef";
		
	}
	
	@GetMapping("/admin/inserisci-chef")
	public String getInserisciChefView(Model model) {
		
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
	
	@GetMapping("/admin/chef/{chefId}/modifica")
	public String getModificaChefView(@PathVariable("chefId") Long chefId,
			Model model) {
		
		model.addAttribute("chef", chefService.findById(chefId));
		
		return "modifica-chef";
		
	}
	
	@PostMapping("/admin/chef/{chefId}/modifica")
	public String updateChef(@Valid @ModelAttribute("chef") Chef chef,
			BindingResult chefBindingResult) {
		
		if (!chefBindingResult.hasErrors()) {
			chefService.save(chef);
		}
		
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/chef/{chefId}/cancella")
	public String deleteChef(@PathVariable("chefId") Long chefId) {
		
		chefService.deleteById(chefId);
		
		return "redirect:/admin";
		
	}
	
}
