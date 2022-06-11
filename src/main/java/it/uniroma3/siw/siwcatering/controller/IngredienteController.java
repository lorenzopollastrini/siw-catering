package it.uniroma3.siw.siwcatering.controller;

import javax.servlet.http.HttpServletRequest;
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
	public String createIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,
			BindingResult ingredienteBindingResult,
			@PathVariable("piattoId") Long piattoId,
			Model model) {
		
		Piatto piatto = piattoService.findById(piattoId);
		
		if (!ingredienteBindingResult.hasErrors()) {
			
			Ingrediente savedIngrediente = ingredienteService.save(ingrediente);
			
			piatto.addIngrediente(savedIngrediente);
			piattoService.save(piatto);
			
			return "redirect:/piatto/" + piatto.getId();
			
		}
		
		model.addAttribute("piatto", piatto);
		
		return "inserisci-ingrediente";
		
	}
	
	@GetMapping("/admin/piatto/{piattoId}/ingrediente/{ingredienteId}/modifica")
	public String getModificaIngredienteView(@PathVariable("piattoId") Long piattoId,
			@PathVariable("ingredienteId") Long ingredienteId,
			Model model) {
		
		model.addAttribute("ingrediente", ingredienteService.findById(ingredienteId));
		model.addAttribute("piattoId", piattoId);
		
		return "modifica-ingrediente";
		
	}
	
	@PostMapping("/admin/piatto/{piattoId}/ingrediente/{ingredienteId}/modifica")
	public String updateIngrediente(@PathVariable("piattoId") Long piattoId,
			@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,
			BindingResult ingredienteBindingResult,
			HttpServletRequest request) {
		
		if (!ingredienteBindingResult.hasErrors()) {
			ingredienteService.save(ingrediente);
			return "redirect:/piatto/" + piattoId;
		}
		
		return "modifica-ingrediente";
		
	}
	
	@GetMapping("/admin/piatto/{piattoId}/ingrediente/{ingredienteId}/cancella")
	public String deleteIngrediente(@PathVariable("piattoId") Long piattoId,
			@PathVariable("ingredienteId") Long ingredienteId) {
		
		Ingrediente ingrediente = ingredienteService.findById(ingredienteId);
		
		ingredienteService.delete(ingrediente);
		
		return "redirect:/piatto/" + piattoId;
		
	}

}
