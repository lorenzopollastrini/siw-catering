package it.uniroma3.siw.siwcatering.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.siwcatering.model.Buffet;
import it.uniroma3.siw.siwcatering.model.Chef;
import it.uniroma3.siw.siwcatering.model.Ingrediente;
import it.uniroma3.siw.siwcatering.model.Piatto;
import it.uniroma3.siw.siwcatering.service.BuffetService;
import it.uniroma3.siw.siwcatering.service.ChefService;
import it.uniroma3.siw.siwcatering.service.PiattoService;

@Controller
public class MainController {
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private PiattoService piattoService;

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/admin")
	public String adminHome(Model model) {
		
		Collection<Chef> chefs = chefService.findAll();
		Collection<Buffet> buffets = buffetService.findAll();
		Collection<Piatto> piatti = piattoService.findAll();
		
		model.addAttribute("chefs", chefs);
		model.addAttribute("buffets", buffets);
		model.addAttribute("piatti", piatti);
		
		return "admin-home";
		
	}
	
	@GetMapping("/admin/chef/{chefId}")
	public String getAdminChefView(@PathVariable("chefId") Long chefId,
			Model model) {
		
		Chef chef = chefService.findById(chefId);
		Collection<Buffet> buffets = chef.getBuffet();
		
		model.addAttribute("chef", chef);
		model.addAttribute("buffets", buffets);
		
		return "admin-chef";
		
	}
	
	@GetMapping("/admin/buffet/{buffetId}")
	public String getAdminBuffetView(@PathVariable("buffetId") Long buffetId,
			Model model) {
		
		Buffet buffet = buffetService.findById(buffetId);
		Collection<Piatto> piatti = buffet.getPiatti();
		
		model.addAttribute("buffet", buffet);
		model.addAttribute("piatti", piatti);
		
		return "admin-buffet";
		
	}
	
	@GetMapping("/admin/piatto/{piattoId}")
	public String getAdminPiattoView(@PathVariable("piattoId") Long piattoId,
			Model model) {
		
		Piatto piatto = piattoService.findById(piattoId);
		Collection<Ingrediente> ingredienti = piatto.getIngredienti();
		
		model.addAttribute("piatto", piatto);
		model.addAttribute("ingredienti", ingredienti);
		
		return "admin-piatto";
		
	}
	
}
