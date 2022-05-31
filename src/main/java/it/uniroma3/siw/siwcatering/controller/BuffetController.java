package it.uniroma3.siw.siwcatering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siwcatering.model.Buffet;
import it.uniroma3.siw.siwcatering.model.Chef;
import it.uniroma3.siw.siwcatering.service.BuffetService;
import it.uniroma3.siw.siwcatering.service.ChefService;

@Controller
public class BuffetController {
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/admin/chef/{chefId}/buffet/nuovo")
	public String getInserisciBuffetView(@PathVariable("chefId") Long chefId,
			Model model) {
		
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chef", chefService.findById(chefId));
		
		return "inserisci-buffet";
		
	}
	
	@PostMapping("/admin/chef/{chefId}/buffet")
	public String inserisciBuffet(@Valid @ModelAttribute("buffet") Buffet buffet,
			BindingResult buffetBindingResult,
			@PathVariable("chefId") Long chefId) {
		
		if (!buffetBindingResult.hasErrors()) {
			
			Chef chef = chefService.findById(chefId);
			
			buffet.setChef(chef);
			
			Buffet savedBuffet = buffetService.save(buffet);
			
			chef.addBuffet(savedBuffet);
			chefService.save(chef);
			
		}
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin/buffet/{buffetId}/modifica")
	public String getModificaBuffetView(@PathVariable("buffetId") Long buffetId,
			Model model) {
		
		model.addAttribute("buffet", buffetService.findById(buffetId));
		
		return "modifica-buffet";
		
	}
	
	@PostMapping("/admin/buffet/{buffetId}/modifica")
	public String modificaBuffet(@Valid @ModelAttribute("buffet") Buffet buffet,
			BindingResult buffetBindingResult) {
		
		if (!buffetBindingResult.hasErrors()) {
			buffetService.save(buffet);
		}
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin/buffet/{buffetId}/cancella")
	public String cancellaBuffet(@PathVariable("buffetId") Long buffetId) {
		
		Buffet buffet = buffetService.findById(buffetId);
		Chef chef = buffet.getChef();
		
		chef.removeBuffet(buffet);
		chefService.save(chef);
		
		buffetService.delete(buffet);
		
		return "redirect:/admin";
		
	}

}
