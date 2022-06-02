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

import it.uniroma3.siw.siwcatering.model.Ingrediente;
import it.uniroma3.siw.siwcatering.model.Piatto;
import it.uniroma3.siw.siwcatering.service.IngredienteService;
import it.uniroma3.siw.siwcatering.service.PiattoService;

@Controller
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PiattoService piattoService;
	
	@GetMapping("/admin/piatto/{piattoId}/ingrediente/nuovo")
	public String getInserisciIngredienteView(@PathVariable("piattoId") Long piattoId,
			Model model) {
		
		model.addAttribute("ingrediente", new Ingrediente());
		model.addAttribute("piatto", piattoService.findById(piattoId));
		
		return "inserisci-ingrediente";
		
	}
	
	@PostMapping("/admin/piatto/{piattoId}/ingrediente")
	public String inserisciIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,
			BindingResult ingredienteBindingResult,
			@PathVariable("piattoId") Long piattoId) {
		
		if (!ingredienteBindingResult.hasErrors()) {
			
			Piatto piatto = piattoService.findById(piattoId);
			
			Ingrediente savedIngrediente = ingredienteService.save(ingrediente);
			
			piatto.addIngrediente(savedIngrediente);
			piattoService.save(piatto);
			
		}
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin/ingrediente/{ingredienteId}/modifica")
	public String getModificaIngredienteView(@PathVariable("ingredienteId") Long ingredienteId,
			Model model) {
		
		model.addAttribute("ingrediente", ingredienteService.findById(ingredienteId));
		
		return "modifica-ingrediente";
		
	}
	
	@PostMapping("/admin/ingrediente/{ingredienteId}/modifica")
	public String modificaIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,
			BindingResult ingredienteBindingResult) {
		
		if (!ingredienteBindingResult.hasErrors()) {
			ingredienteService.save(ingrediente);
		}
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin/ingrediente/{ingredienteId}/cancella")
	public String cancellaIngrediente(@PathVariable("ingredienteId") Long ingredienteId) {
		
		Ingrediente ingrediente = ingredienteService.findById(ingredienteId);
		
		ingredienteService.delete(ingrediente);
		
		return "redirect:/admin";
		
	}

}
