package it.uniroma3.siw.siwcatering.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siwcatering.model.Buffet;
import it.uniroma3.siw.siwcatering.model.Chef;
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
		return "redirect:/chef";
	}
	
	@GetMapping("/admin")
	public String adminHome(Model model) {
		
		Collection<Chef> chefs = chefService.findAllByOrderByCognome();
		Collection<Buffet> buffets = buffetService.findAllByOrderByNome();
		Collection<Piatto> piatti = piattoService.findAllByOrderByNome();
		
		model.addAttribute("chefs", chefs);
		model.addAttribute("buffets", buffets);
		model.addAttribute("piatti", piatti);
		
		return "admin-home";
		
	}
	
}
